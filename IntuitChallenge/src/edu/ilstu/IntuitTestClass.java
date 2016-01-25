/**
 * 
 */
package edu.ilstu;

/**
 * @author Ryan Throw
 *
 */
public class IntuitTestClass {

	public static void main(String[] args) {
		//initialize objects
		IntArraySorter sorter = new IntArraySorter();
		PrimeNumberFinder primeFinder = new PrimeNumberFinder();
		JsonPathFinder pathFinder = new JsonPathFinder();
		
		int[] testArray = new int[]{1,10,5,63,29,71,10,12,44,29,10,-1};
		
		System.out.println("-----------------------------Sorting an Integer Array-----------------------------");
		sorter.sort(testArray);
		
		System.out.println("------------------------------Finding Prime Numbers-------------------------------");
		primeFinder.findPrimeNumber(3);
		primeFinder.findPrimeNumber(58);
		primeFinder.findPrimeNumber(10001);
		
		System.out.println("\n----------------------------------JSON Parsing------------------------------------");
		pathFinder.parseJsonFile("json.txt");
		pathFinder.findNodeByData(pathFinder.getRoot(), "item2");
		pathFinder.findNodeByData(pathFinder.getRoot(), "SubItem 2");
		pathFinder.findNodeByData(pathFinder.getRoot(), "SubItem 2 item1");
	}
}
