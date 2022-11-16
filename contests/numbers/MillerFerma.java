package numbers;

import java.util.*;

public class MillerFerma {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		Miller_Rabin_test(n);
		miniFerma(n);
	}
	
	public static int fastMODpow(int n, int exp, int mod)
	{
	    int res = n, res1 = (int)Math.pow(n, exp & 1) % mod;
	    while (exp > 0)
	    {
	        res = ((res % mod) * (res % mod)) % mod;
	        exp >>= 1;
	        res1 = (res1 * ((int)Math.pow(res, exp & 1) % mod)) % mod;
	    }
	    return res1;
	}

	public static void miniFerma(int n)
	{

	    int flag = 1;
	    int count = 0;

	    for (int i = 1; i < n; ++i)
	    {
	        if (fastMODpow(i, n - 1, n) != 1)
	            flag = 0;
	        else
	            count++;
	    }

	    System.out.printf("Fermat test: %s %d %d", flag == 1 && n != 1 ? "True" : "False", n - count - 1, count);
	}

	public static void Miller_Rabin_test(int n)
	{
	    int flag = 1;
	    int count = 0;
	    int count1 = 0;
	    int count2 = 0;
	    int degree = 1;
	    for (; (n > 1) && (n - 1) % (int)Math.pow(2, degree) == 0; ++degree)
	        ;
	    int d = (n - 1) / (int)Math.pow(2, degree - 1);

	    for (int a = 1; a < n; a++)
	    {
	        int f = 1;
	        if (fastMODpow(a, d, n) == 1)
	        {
	            count++;
	            count1++;
	            continue;
	        }

	        for (int i = 0; i < degree - 1 && f != 0; ++i)
	        {
	            if (fastMODpow(a, (int)Math.pow(2, i) * d, n) == n - 1)
	            {
	                count++;
	                count2++;
	                f = 0;
	                continue;
	            }
	        }

	        if (f == 0)
	        {
	            continue;
	        }

	        flag = 0;
	    }

	    System.out.printf("Miller-Rabin test: %s %d %d %d\n", flag == 1 && n != 1? "True" : "False", n - count - 1, count1, count2);
	}
}
