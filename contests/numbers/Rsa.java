package numbers;

import java.util.*;
import java.math.*;

public class Rsa {
	static Scanner in = new Scanner(System.in);
	static Key openKey = new Key();
	static Key privateKey = new Key();
	static BigInteger mas[] = new BigInteger[3];

	public static void main(String[] args) {
		BigInteger p1 = BigInteger.valueOf(in.nextInt());
		BigInteger p2 = BigInteger.valueOf(in.nextInt());
		BigInteger e = BigInteger.valueOf(in.nextInt());
		in.nextLine();

		BigInteger fi = p1.subtract(BigInteger.ONE).multiply(p2.subtract(BigInteger.ONE));
		openKey.mod = p1.multiply(p2);
		privateKey.mod = openKey.mod;
//		System.out.println(openKey.mod.toString());
		for (;; e = e.add(BigInteger.ONE)) {
			if (miniFerma(e) > 0) {
				extEvklid(e, fi);
				if (mas[0].compareTo(BigInteger.valueOf(1)) == 0)
					break;
			}
		}
		openKey.crypt = e;

		BigInteger d = mas[1].add(fi.multiply(mas[1])).mod(fi);
		privateKey.crypt = d;

		List<Integer> ans = readE();
		Iterator<Integer> it = ans.iterator();

		BigInteger res1 = BigInteger.valueOf(0);
		while (it.hasNext()) {
			res1 = res1.add(BigInteger.valueOf(it.next()));
			if (it.hasNext()) {
				res1 = res1.shiftLeft(8);
			}
		}

		BigInteger res = res1.modPow(openKey.crypt, openKey.mod);
		Stack<Integer> enBytes = new Stack<Integer>();
		while (res.compareTo(BigInteger.ZERO) > 0) {
//			System.out.println(res + " : " + res.remainder(BigInteger.valueOf(256)));
			enBytes.push(res.remainder(BigInteger.valueOf(256)).intValue());
			res = res.divide(BigInteger.valueOf(256));
		}

		System.out.printf("Private: %s %s\n", privateKey.crypt.toString(), privateKey.mod.toString());
		System.out.printf("Public: %s %s\n", openKey.crypt.toString(), openKey.mod.toString());

		System.out.printf("Initial bytes: ");
		for (Integer i : ans) {
			System.out.printf("%d ", i);
		}

//		Queue<Integer> eBytes = new LinkedList<Integer>();
		System.out.printf("\nEncrypted bytes: ");
		while (!enBytes.empty()) {
//			eBytes.add(enBytes.peek());
			System.out.printf("%d ", enBytes.pop());
		}

	}

	public static int miniFerma(BigInteger n) {
		return n.isProbablePrime(20) ? 1 : 0;
	}

	static ArrayList<Integer> readE() {
		char[] string = in.nextLine().toCharArray();
		ArrayList<Integer> str = new ArrayList<Integer>();
		for (int i = 0; i < string.length; i++) {
			str.add((int) string[i]);
		}
		return str;
	}

	static void extEvklid(BigInteger a, BigInteger b) {
		if (b.compareTo(BigInteger.ZERO) == 0) {
			mas[0] = a;
			mas[1] = BigInteger.valueOf(1);
			mas[2] = BigInteger.valueOf(0);
			return;
		}
		extEvklid(b, a.mod(b));
		BigInteger i = mas[1], j = mas[2];
		mas[1] = j;
		mas[2] = i.subtract(a.divide(b).multiply(j));
		return;
	}
}

class Key {
	public BigInteger crypt;
	public BigInteger mod;
}
