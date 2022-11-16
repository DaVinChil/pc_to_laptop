package search;

import java.util.*;

public class LinearSearch {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int num = in.nextInt();
		int arr[] = new int[n];
		ArrayList<Integer> pos = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}

		System.out.println("Initial array: ");

		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();

		System.out.println("Target element: " + num);
		for (int i = 0; i < n; i++) {
			if (num == arr[i]) {
				pos.add(i);
			}
		}

		if (pos.size() > 0) {
			System.out.print("Target is found at: ");
			for (int i : pos) {
				System.out.print(i + " ");
			}
		} else {
			System.out.print("No targets");
		}
	}

}
