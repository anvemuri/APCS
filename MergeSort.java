import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MergeSort {
	private Scanner console = new Scanner(System.in);
	ArrayList<Comparable> myArray = new ArrayList<Comparable>();

	/**
	 * Sorts any ArrayList of Comparable objects using Selection Sort.
	 * 
	 * @param list
	 *            reference to an array of integers to be sorted
	 */
	public void selectionSort(ArrayList<Comparable> list) {

		for (int row = 0; row < list.size(); row++) {
			int min = row;
			for (int col = list.size() - 1; col > row; col--) {
				if (list.get(col).compareTo(list.get(min)) < 0) {
					min = col;
				}
			}
			Comparable temp = list.get(row);
			list.set(row, list.get(min));
			list.set(min, temp);
		}
	}

	/**
	 * Write a merge method to merge two sorted lists.
	 * 
	 * Preconditions: Lists A and B are sorted in nondecreasing order. Action:
	 * Lists A and B are merged into one list, C. Postcondition: List C contains
	 * all the values from Lists A and B, in nondecreasing order.
	 */
	public void merge(ArrayList<Comparable> a, ArrayList<Comparable> b,
			ArrayList<Comparable> c) {
		int one = 0;
		int two = 0;

		while (one < a.size() && two < b.size()) {
			if (a.get(one).compareTo(b.get(two)) < 0) {
				System.out.println("hi");
				c.add(a.get(one));
				// a.remove(one);
				one++;
			} else {
				c.add(b.get(two));
				two++;
				// c.remove(two);
			}
			System.out.println("MERGEbefore = " + c);
		}
		if (one >= a.size()) {
			for (int j = two; j < b.size(); j++) {
				c.add(b.get(j));
			}
		}

		if (two >= b.size()) {
			for (int i = one; i < a.size(); i++) {
				c.add(a.get(i));
			}
		}
		System.out.println("MergeAfter = " + c);
	}

	/**
	 * Write a method to - Ask the user how many numbers to generate - Ask the
	 * user to enter the largest integer to generate - Initialize an ArrayList
	 * of random Integers from 1 to largestInt - Return the ArrayList
	 * 
	 * @return an ArrayList of size specified by the user filled with random
	 *         numbers
	 */
	public ArrayList<Comparable> fillArray() {
		System.out.print("How many numbers do you wish to generate? ");
		int numInts = console.nextInt();
		System.out.print("Largest integer to generate? ");
		int largestInt = console.nextInt();

		Random randGen = new Random();
		myArray = new ArrayList<Comparable>();

		for (int loop = 0; loop < numInts; loop++) {
			Integer x = new Integer(randGen.nextInt(largestInt) + 1);
			myArray.add(x);
		}
		return myArray;
	}

	/**
	 * Write a method to print out the contents of the ArrayList in tabular
	 * form, 20 columns. You can use the \t escape character or use printf to
	 * format using fields.
	 */
	public void screenOutput(ArrayList<Comparable> temp) {
		for (int loop = 0; loop < temp.size(); loop++) {
			if (loop % 12 == 0) {
				System.out.println();
			}
			System.out.print(temp.get(loop) + "  ");
		}
		System.out.println();
	}
}
