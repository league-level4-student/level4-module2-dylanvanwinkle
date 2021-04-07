package StringMethods;

import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return s1;
		} else if (s2.length() > s1.length()) {
			return s2;
		} else {
			return s2;
		}
	}

	// if String s contains the word "underscores", change all of the spaces to
	// underscores
	public static String formatSpaces(String s) {
		if (s.contains("underscores")) {
			s = s.replace(' ', '_');
		}
		return s;
	}

	// Return the name of the person whose LAST name would appear first if they were
	// in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		int important;
		int important2;
		String s1New = s1.trim();
		String s2New = s2.trim();
		String s3New = s3.trim();
		int i1 = s1New.indexOf(' ');
		s1New = s1New.substring(i1);
		int i2 = s2New.indexOf(' ');
		s2New = s2New.substring(i2);
		int i3 = s3New.indexOf(' ');
		s3New = s3New.substring(i3);
		int i1New = s1New.compareTo(s2New);
		if (i1New < 0) {
			important = s1New.compareTo(s3New);
			if (important > 0) {
				return s3.trim();
			} else if (important < 0) {
				return s1.trim();
			} else {
				return s3.trim();
			}
		} else if (i1New > 0) {
			important2 = s2New.compareTo(s3New);
			if (important2 > 0) {
				return s3.trim();
			} else if (important2 < 0) {
				return s2.trim();
			} else {
				return s2.trim();
			}
		}
		return null;
	}

	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int value = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				value = value + Character.getNumericValue(s.charAt(i));
			}
		}
		return value;
	}

	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int counter = 0;
		int loc = s.indexOf(substring);
		while (loc != -1) {
			counter++;
			loc = s.indexOf(substring, loc + substring.length());
		}

		return counter;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		byte[] sNew = s.getBytes();
		String key2 = key + "";
		byte[] keyNew = key2.getBytes();
		String message = Utilities.encrypt(sNew, keyNew[0]);
		return message;
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		String key2 = key + "";
		byte[] keyNew = key2.getBytes();
		String message = Utilities.decrypt(s, keyNew[0]);
		return message;
	}

	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		int counter = 0;
		String[] sa = s.split(" ");
		for (int i = 0; i < sa.length; i++) {
			if (sa[i].endsWith(substring)) {
				counter++;
			}
		}
		return counter;
	}

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		int value = 0;
		int loc1 = s.indexOf(substring);
		int loc2 = s.lastIndexOf(substring);
		loc1 += substring.length();
		value = loc2 - loc1;
		return value;
	}

	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		String sNew = "";
		String sNew2 = "";
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i))) {
				sNew2 += s.charAt(i);
			}
			if (Character.isLetter(s.charAt(s.length() - (i + 1)))) {
				sNew += s.charAt(s.length() - (i + 1));
			}
		}
		
		if (sNew2.equalsIgnoreCase(sNew)) {
			return true;
		}
		return false;

	}
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
