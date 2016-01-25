/**
 * 
 */
package edu.ilstu;

/**
 * @author Ryan Throw
 *
 */
public class PrimeNumberFinder {
	
	//find prime numbers	
	public void findPrimeNumber(int desiredPrimeNum){
		
		int count = 2;
		int currentPrimeNum = 0;
		
		//check if the desired prime number is large, if so
		//inform the user it will take a short time
		if(desiredPrimeNum > 5000){
			System.out.println("The Prime number " + desiredPrimeNum + " is large, please wait...");
		}
		
		//keep iterating through numbers until the desired prime number is found
		while(currentPrimeNum != desiredPrimeNum){
			boolean isPrime = true;
			
			//iterate through dividends 2 - half the number in question
			//if any divide perfectly then the number is not prime
			for(int i = 2; i <= count / 2; i++){
				if(count % i == 0){
					isPrime = false;
				}
			}	
			
			//if the current number is prime then add one to currentPrimeNum
			//and move on to the next number.
			if(isPrime == true){
				currentPrimeNum ++;
			}
			count ++;
		}
		
		//print results to screen
		System.out.println("Prime number " + currentPrimeNum + " is equal to: " + (count - 1));
	}
}
