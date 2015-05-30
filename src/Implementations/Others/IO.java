package Implementations.Others;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import Enums.Symbols;


public interface IO {

	public static <T> void print(T s) { System.out.print(s); }
	
	public static <T> void print(List<T> list) { list.forEach(System.out::print); }
	
	public static <T> void print(File file) {
		try { FileUtils.writeStringToFile(file, ""); } catch (Exception ex) { ex.printStackTrace(); } }
	
	public static <T> void print(File file, T s) {
		try { FileUtils.writeStringToFile(file, String.valueOf(s)); } catch (Exception ex) { ex.printStackTrace(); } }
	
	
	public static <T> void print(File file, List<T> list) { 
		try { String line = list.stream().map(e -> String.valueOf(e)).reduce(emptyString, (a, e) -> a+e);
			print(file, line); } catch (Exception ex) {ex.printStackTrace(); } }
	
	
	public static <T> void printL(T s) { System.out.println(s); }
	
	public static <T> void printL(List<T> list) { list.forEach(System.out::println); }
	
	public static <T> void printL(File file, List<T> list) { 
		String line = list.stream().map(e -> String.valueOf(e)).reduce(emptyString, (a, e) -> a+e+newLine);
		print(file, line); }
	
	@SuppressWarnings("finally")
	public static List<String> scan(File file) { 
		List <String> list = null;
		try { list = FileUtils.readLines(file); } 
		catch (IOException e) { e.printStackTrace(); } 
		finally { return list; } }
	
	@SuppressWarnings("finally")
	public static String scanS(File file) {
		String line = "";
		try { line = FileUtils.readLines(file).stream().map(e -> String.valueOf(e)).reduce(emptyString, (a, e) -> a+e); } 
		catch (IOException e) { e.printStackTrace(); } 
		finally { return line; }
	}
	
	@SuppressWarnings("finally")
	public static String scanSL(File plik) {
		String line = "";
		try { line = FileUtils.readLines(plik).stream().map(e -> String.valueOf(e)).reduce(emptyString, (a, e) -> a+e+newLine); }
		catch (Exception e) {e.printStackTrace();}
		finally { return line; } }
	
	public static Boolean isFileExists(File file) { return file.exists(); }
	
	public static Boolean isFileExists(String nazwa) {return new File(nazwa).exists(); }
	
	public static void edit(File file, Integer editingNumber, String newValue) {
		List <String> list = IO.scan(file);
		list.set(editingNumber, newValue);
		IO.printL(file, list); }
	
	public static String append(String msg, String textToAppends) {
		return msg += textToAppends; }
	
	public static <T> List <T> append(List<T> source, List<T> target) {
		List <T> sourceList = source;
		List <T> targetList = target;
		targetList.forEach( s -> sourceList.add(s) );
		return sourceList; }
	
	public static <T> T initObject(T o ) { return o; }
	
	public static final String emptyString = initObject(Symbols.EMPTY_STRING.getChar());
	public static final String newLine = initObject(Symbols.NEW_LINE.getChar());

}
