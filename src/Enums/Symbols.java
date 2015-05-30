package Enums;

public enum Symbols {
	
	EMPTY_STRING	(""), 
	SPACJA			(" "),
	NEW_LINE		("\n"), 
	POWRÓT_KARETKI	("\r"), 
	CUDZYSŁÓW		("\""), 
	APOSTROF		("\'"), 
	BACKSLASH		("\\");
	
	private final String znak;
	
	private Symbols(String znak) { this.znak = znak; }
	
	public String getChar() { return znak; }

}
