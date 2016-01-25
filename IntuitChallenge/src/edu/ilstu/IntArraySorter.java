/**
 * 
 */
package edu.ilstu;

/**
 * @author Ryan Throw
 *
 */
public class IntArraySorter {
	
	public void sort(int[] testArray){
		//print the start message to the screen
		printMessage(testArray);
		
		//sort
		for(int i = 0; i < testArray.length; i++){
			int lowestVal = i;
			for(int j = i; j < testArray.length; j++){
				if(testArray[j] < testArray[lowestVal]){
					lowestVal = j;
				}
			}
			//swap the lowest value with the current value with index i
			int tempVal = testArray[i];
			testArray[i] = testArray[lowestVal];
			testArray[lowestVal] = tempVal;
		}
		
		//print the sorting results to the screen
		printResults(testArray);
	}
	
	private void printMessage(int[] testArray){
		//print the message to the screen
		if(testArray.length > 0){
			System.out.print("Sorting the numbers: " + testArray[0]);
			
			for(int i = 1; i < testArray.length; i++){
				System.out.print(", " + testArray[i]);
			}
			
			System.out.println(" in numeric order");
		}
		else{
			System.out.println("Error, no numbers to sort");
		}
	}
	
	private void printResults(int[] testArray){
		//print the sorting results to the screen
		if(testArray.length > 0){
			System.out.print("\nSorting complete: "+ testArray[0]);
			for(int i = 1; i < testArray.length; i++){
				System.out.print(", " + testArray[i]);
			}
		}
		else{
			System.out.println("Error, no result to display");
		}
		System.out.println("\n\n");
	}
}
