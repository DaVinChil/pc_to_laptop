package numbers;

import java.util.*;

public class Sieve {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int l = in.nextInt();
	    int r = in.nextInt();

	    System.out.printf("Start and finish: %d %d\n", l, r);

	    ArrayList<Integer> term = new ArrayList<>();
	    int[] primes = new int[r - l + 1];

	    int iter = 0;
	    int countM = 0;

	    for (int i = 2; i <= (int)Math.sqrt(r); i++){
	        int c = 0;

	        if (miniFerma(i) > 0)
	        {
	        	iter++;
	            for (int j = l % i == 0 ? 0 : i - (l % i); j < r - l + 1; j += i)
	            {
	                if (j + l != i && primes[j] != 1)
	                {
	                    term.add(c++, j + l);
	                    countM++;
	                    primes[j] = 1;
	                }
	            }
	        }

	        if(c > 0) {
	            System.out.printf("Iteration : %d\n", iter);
	            for(int j = 0; j < c; j++) {
	                System.out.printf("%d ", term.get(j));
	            }
	            System.out.printf("\n");
	        }

	    }

	    System.out.printf("Primes :\n");
	    if (countM < r - l + 1){
	        for (int i = 0; i < r - l + 1; i++){
	            if (primes[i] == 0){
	                System.out.printf("%d ", i + l);
	            }
	        }
	    } else {
	        System.out.printf("No primes");
	    }
	}

	public static int fastMODpow(int n, int exp, int mod) {
		int res = n, res1 = (int) Math.pow(n, exp & 1) % mod;
		while (exp != 0) {
			res = ((res % mod) * (res % mod)) % mod;
			exp >>= 1;
			res1 = (res1 * ((int) Math.pow(res, exp & 1) % mod)) % mod;
		}
		return res1;
	}

	public static int miniFerma(int n) {
		if (n >= 2) {
			if (n < 4)
				return 1;
			if (n % 2 == 0)
				return 0;
			for (int i = 0; i < 100; ++i) {
				int a = 2 + ((int)Math.random()) % (n - 4);
				if (fastMODpow(a, n - 1, n) != 1)
					return 0;
			}
			return 1;
		} else
			return 0;
	}

}
