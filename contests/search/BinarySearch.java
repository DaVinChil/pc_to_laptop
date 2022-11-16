package search;

import java.util.*;

public class BinarySearch {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int targ = in.nextInt();
		int arr[] = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}

		System.out.println("Initial array: ");

		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}

		System.out.println();

		System.out.println("Target element: " + targ);
		int i = 0, j = arr.length - 1;

		while (i < j) {
			System.out.print("Search history: ");
			int mid = (i + j) / 2;
			System.out.print(arr[mid] + "(" + mid + ") ");
			if (arr[mid] < targ) {
				i = mid + 1;
			} else if (arr[mid] > targ) {
				j = mid - 1;
			} else {
				i = mid;
				break;
			}
		}
		if (arr.length > 1) {
			System.out.println();
		}
		if (arr[i] == targ) {
			System.out.println("Target is found at: " + i);
		} else {
			System.out.println("No targets");
		}
	}

}
