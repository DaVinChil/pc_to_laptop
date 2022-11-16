package search;

import java.util.*;

public class ExpSearch {

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

		System.out.print("Bound history: ");
		int bound = 0;
		int i = 0;
		System.out.printf("%d(%d) ", arr[bound], bound);
		while (arr[bound] < targ) {
			bound *= 2;
			if (bound == 0)
				bound = 1;
			if (bound >= arr.length) {
				i = bound / 2;
				bound = arr.length - 1;
				break;
			}

			System.out.printf("%d(%d) ", arr[bound], bound);
		}
		System.out.println();

		i = i == 0 ? bound / 2 : i;
		int j = bound;
		if (arr[j] < targ) {
			System.out.println("No targets");
		} else if (arr[j] == targ) {
			System.out.println("Target is found at: " + j);
		} else if (arr[i] == targ) {
			System.out.println("Target is found at: " + i);
		} else if (j > 0) {
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

			System.out.println();

			if (arr[i] == targ) {
				System.out.println("Target is found at: " + i);
			} else {
				System.out.println("No targets");
			}

		} else {
			System.out.println("No targets");
		}

	}

}
