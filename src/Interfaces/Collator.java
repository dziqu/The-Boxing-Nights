package Interfaces;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Collator {
	
	public static final Comparator <String> 		cStr 	= (s1, s2) 		-> s1.compareTo(s2);
	public static final Comparator <Integer> 		cInt 	= (i1, i2) 		-> i1.compareTo(i2);
	public static final Comparator <BigInteger>	cBInt 	= (bi1, bi2) 	-> bi1.compareTo(bi2);
	public static final Comparator <Float> 		cFloat 	= (f1, f2) 		-> f1.compareTo(f2);
	public static final Comparator <Double> 		cDouble = (d1, d2) 		-> d1.compareTo(d2);
	public static final Comparator <Boolean> 		cBool 	= (b1, b2) 		-> b1.compareTo(b2);
	public static final Comparator <Character> 	cChar 	= (c1, c2) 		-> c1.compareTo(c2);
	
	public static Integer collateTo(String s1, String s2) 			{ return cStr.compare(s1, s2); }
	
	public static Integer collateTo(Integer i1, Integer i2) 		{ return cInt.compare(i1, i2); }
	
	public static Integer collateTo(BigInteger bi1, BigInteger bi2) { return cBInt.compare(bi1, bi2); }
	
	public static Integer collateTo(Float f1, Float f2) 			{ return cFloat.compare(f1, f2); }
	
	public static Integer collateTo(Double d1, Double d2) 			{ return cDouble.compare(d1, d2); }
	
	public static Integer collateTo(Boolean b1, Boolean b2) 		{ return cBool.compare(b1, b2); }
	
	public static Integer collateTo(Character c1, Character c2) 	{ return cChar.compare(c1, c2); }
	
	public static Boolean regexNumber(String msg) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher matcher = pattern.matcher(msg);
		return matcher.matches(); }

}
