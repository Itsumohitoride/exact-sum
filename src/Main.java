import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * @author Luis Miguel Ossa Arias
 *
 */
public class Main {

	public final static String SEPARATOR = " ";
	public static int position = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
		
		String numBooks;
		String pricesEveryBook;
		String moneyPeter;
		String [] pricesBooks;
		int[] integerPricesBooks;
		String message;

		numBooks = br.readLine();
		
		while(numBooks != null) {

			int numBooksToPrice = Integer.parseInt(numBooks);

			pricesEveryBook = br.readLine();
			pricesBooks = pricesEveryBook.split(SEPARATOR);

			integerPricesBooks = new int[numBooksToPrice];

			for (int i = 0; i < pricesBooks.length; i++) {

				int priceOneByOne = Integer.parseInt(pricesBooks[i]);

				integerPricesBooks[i] = priceOneByOne;
			}

			moneyPeter = br.readLine();

			int moneyPeterHas = Integer.parseInt(moneyPeter);

			message = booksToBuy(moneyPeterHas, integerPricesBooks);
			
			bw.write(message+"\n\n");
			
			numBooks = br.readLine();
			numBooks = br.readLine();
		}

		br.close();
		bw.close();
	}
	
	public static String booksToBuy(int moneyPeterHas, int[] integerPricesBooks) {
		
		String message = "";
		int difference = 100000;
		int bookOne = 0;
		int bookTwo = 0;
		
		Arrays.sort(integerPricesBooks);
		
		for (int i = 0; i < integerPricesBooks.length; i++) {
			
			if(bookOne == 0 && bookTwo == 0 && i - position != 0) {
				
				bookOne = integerPricesBooks[i];
				bookTwo = binarySearch(integerPricesBooks,integerPricesBooks[i],moneyPeterHas);
				
				if(bookOne > bookTwo) {
					difference = bookOne-bookTwo;
				}
				else{
					difference = bookTwo-bookOne;
				}
			}
			else {
				if(integerPricesBooks[i] - binarySearch(integerPricesBooks,integerPricesBooks[i],moneyPeterHas) < difference && integerPricesBooks[i] + binarySearch(integerPricesBooks,integerPricesBooks[i],moneyPeterHas) == moneyPeterHas && i - position != 0) {

					difference = bookOne - bookTwo;
					bookOne = integerPricesBooks[i];
					bookTwo = binarySearch(integerPricesBooks,integerPricesBooks[i],moneyPeterHas);
					
					if(bookOne > bookTwo) {
						difference = bookOne-bookTwo;
					}
					else{
						difference = bookTwo-bookOne;
					}
				}
			}
		}
		
		message = "Peter should buy books whose prices are "+bookOne+" and "+bookTwo+".";
		
		return message;
	}
	
	public static int binarySearch(int[] disorganizedList, int x, int moneyOfPeter) {
		
		int numberInThatPosition = -1;
		int i = 0;
		int j = disorganizedList.length-1;
		
		while(i <= j && numberInThatPosition < 0) {
			
			int m = (i+j)/2;
			
			if(disorganizedList[m]+x == moneyOfPeter) {
				numberInThatPosition = disorganizedList[m];
				position = m;
			}
			else if(disorganizedList[m]+x > moneyOfPeter) {
				j = m-1;
			}
			else if(disorganizedList[m]+x < moneyOfPeter){
				i = m+1;
			}
		}
		
		return numberInThatPosition;
	}
}