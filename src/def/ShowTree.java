package def;

import java.util.*;

public class ShowTree {
	static Scanner in = new Scanner(System.in);
	static Integer[] res;
	static int am = 0;

	public static void main(String[] args) {
		Integer[] arr = Arrays.stream(in.nextLine().replace(",", "").split(" ")).map(item -> Integer.parseInt(item))
				.toArray(Integer[]::new);
		int n = arr.length;
		res = new Integer[n];
		res[0] = arr[n / 2];

		buildTree(Arrays.copyOfRange(arr, 0, arr.length / 2), 0, 1);
		buildTree(Arrays.copyOfRange(arr, arr.length / 2 + 1, arr.length), 0, 2);

		int st = 0;
		int stages = (int) log2(n + 1) - 1;
		for (int i = 1; i <= (int) log2(n + 1); i++) {
			int j = 0;
			for (j = st; j < n && j < st + ((int) Math.pow(2, i - 1)); j++) {
				for (int k = 0; k < Math.pow(2, stages) - 1; k++) { System.out.print("  "); }
				System.out.print(res[j] + "  ");
				for (int k = 0; k < Math.pow(2, stages) - 1; k++) { System.out.print("  "); }
			}
			st = j;
			stages--;
			System.out.println();
		}
	}
	
	public static int log2(int N)
    {
        int result = (int)(Math.log(N) / Math.log(2));
        return result;
    }

	public static void buildTree(Integer[] arr, int parent, int child) {
		if (arr.length == 0) {
			return;
		}

		res[parent * 2 + child] = arr[arr.length / 2];

		buildTree(Arrays.copyOfRange(arr, 0, arr.length / 2), parent * 2 + child, 1);
		buildTree(Arrays.copyOfRange(arr, arr.length / 2 + 1, arr.length), parent * 2 + child, 2);
	}

}
