package def;

import java.util.*;

public class RabinCarp {
	
	public static void main(String[] args) {
		String source = "Alibaba or Alibubab? I do not know!";
		String pattern = "b*b";
		ArrayList<String> found = find(source, pattern);
		if(!found.isEmpty()) {
			System.out.println("Found coincidences:");
			found.forEach(System.out::println);
		} else {
			System.out.println("No coincidences");
		}
	}
	
	public static ArrayList<String> find(String source, String pattern){
		ArrayList<String> found = new ArrayList<>();
		
		int ptrnHash = calcHash(pattern);
		int winHash = 0;
		ptrnHash -= '*';
		int astrPos = findAster(pattern);
		outer: for(int start= 0; start < source.length() - pattern.length() + 1; start++) {
			if(start == 0) {
				winHash = calcHash(source.substring(start, pattern.length()));
				winHash -= source.charAt(astrPos);
			} else {
				winHash -= source.charAt(start - 1);
				winHash += source.charAt(start + pattern.length() - 1);
				winHash -= source.charAt(start + astrPos);
			}
			
			if(winHash == ptrnHash) {
				for(int i = 0; i < pattern.length(); i++) {
					if(pattern.charAt(i) != '*' && source.charAt(start + i) != pattern.charAt(i)) {
						continue outer;
					}
				}
				
				found.add(source.substring(start, start + pattern.length()));
			}
			
			winHash += source.charAt(start + astrPos);
		}
		
		return found;
	}
	
	public static int findAster(String pattern) {
		for(int i = 0; i < pattern.length(); i++) {
			if(pattern.charAt(i) == '*') {
				return i;
			}
		}
		
		return -1;
	}
	
	public static int calcHash(String str) {
		int hash = 0;
		for(int i = 0; i < str.length(); i++) {
			hash += str.charAt(i);
		}
		return hash;
	}

}
