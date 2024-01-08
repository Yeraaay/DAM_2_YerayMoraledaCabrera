package LeetCode;

import java.util.Scanner;

class Palindrome_Number {
	
	static Scanner sc = new Scanner(System.in);
	static int numeroEjemplo = 123;
	static int[] arrayNum;
	
	public static boolean isPalindrome(int x) {
		String tranformInteger = Integer.toString(x);
		String backNumber = backNumber(tranformInteger);
		return tranformInteger.equals(backNumber);
	}
	
	public static String backNumber(String numberString) {
		
		char[] charNums = numberString.toCharArray();
		String backNum = "";
		for (int i=charNums.length - 1; i>=0; i--) {
			backNum += charNums[i];
		}
		return backNum;
	}
	
	public static void main(String[] args) {
		System.out.println("Introduce un número: ");
		int numberGiven = sc.nextInt();
		
		System.out.println(isPalindrome(numberGiven));
		
	}
	
	
}