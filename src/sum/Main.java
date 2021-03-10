package sum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	public final static String SEPARATOR = " ";

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
		
		int numBooksToPrice = Integer.parseInt(numBooks);
		
		pricesEveryBook = br.readLine();
		pricesBooks = pricesEveryBook.split(SEPARATOR);
		
		integerPricesBooks = new int[pricesBooks.length];
		
		for (int i = 0; i < pricesBooks.length; i++) {
			
			int priceOneByOne = Integer.parseInt(pricesBooks[i]);
			
			integerPricesBooks[i] = priceOneByOne;
		}
		
		moneyPeter = br.readLine();
		
		int moneyPeterHas = Integer.parseInt(moneyPeter);
		
		message = booksToBuy(moneyPeterHas, integerPricesBooks);
		
		br.close();
		bw.close();
	}
	
	public static String booksToBuy(int moneyPeterHas, int[] integerPricesBooks) {
		
		String message = "";
		
		return message;
	}

}
