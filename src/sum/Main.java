package sum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

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

		integerPricesBooks = new int[numBooksToPrice];

		for (int i = 0; i < pricesBooks.length; i++) {

			int priceOneByOne = Integer.parseInt(pricesBooks[i]);

			integerPricesBooks[i] = priceOneByOne;
		}

		moneyPeter = br.readLine();

		int moneyPeterHas = Integer.parseInt(moneyPeter);

		message = booksToBuy(moneyPeterHas, integerPricesBooks);
		
		bw.write(message);
		bw.flush();
		
		br.close();
		bw.close();
	}

	public static String booksToBuy(int moneyPeterHas, int[] integerPricesBooks) {

		String message = "";

		int[] listOfPriceOne = integerPricesBooks;
		int moneyOfPeter = moneyPeterHas;
		int sum = 0;
		int resultOfSearch = 0;
		int bookOne = 0;
		int bookTwo = 0;
		int difference = 0;
		List<Integer> listOfResults = new ArrayList<>();

		for (int i = 0; i < listOfPriceOne.length; i++) {
			for (int j = 0; j < listOfPriceOne.length; j++) {

				sum = listOfPriceOne[i]+listOfPriceOne[j];

				addSorted(listOfResults,sum);
			}
		}
		
		resultOfSearch = binarySearch(listOfResults,moneyOfPeter);
		
		if(resultOfSearch > -1) {
			for (int i = 0; i < listOfPriceOne.length; i++) {
				for (int j = 0; j < listOfPriceOne.length; j++) {

					if(listOfPriceOne[i]+listOfPriceOne[j] == moneyOfPeter) {
						if(bookOne == 0 && bookTwo == 0) {
							bookOne = listOfPriceOne[0];
							bookTwo = listOfPriceOne[0];
							difference = bookOne-bookTwo;
						}
						else {
							if(listOfPriceOne[i] - listOfPriceOne[j] < difference && listOfPriceOne[i] + listOfPriceOne[j] == moneyOfPeter) {
								
								difference = listOfPriceOne[i] - listOfPriceOne[j];
								bookOne = listOfPriceOne[i];
								bookTwo = listOfPriceOne[j];
							}
						}
					}
				}
			}
		}
		
		message = "Peter should buy  books whose prices are "+bookOne+" and "+bookTwo;
		
		return message;
	}
	
	public static int binarySearch(List<Integer> resultsList, int x) {
		
		int position = -1;
		int i = 0;
		int j = resultsList.size()-1;
		
		while(i <= j && position < 0) {
			
			int m = (i+j)/2;
			
			if(resultsList.get(m) == x) {
				position = m;
			}
			else if(resultsList.get(m) > x) {
				j = m-1;
			}
			else {
				i = m+1;
			}
		}
		
		return position;
	}

	public static void addSorted(List<Integer> listOfResults, int newValue) {

		if(listOfResults.isEmpty()) {
			listOfResults.add(newValue);
		}
		else {
			int i = 0;
			while(i < listOfResults.size() && listOfResults.get(i) < newValue) {
				i++;			
			}
			listOfResults.add(i,newValue);
		}
	}
}
