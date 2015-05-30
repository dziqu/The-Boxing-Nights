package Enums;

public enum DefaultControlSettings {
	
	NUMBER_OF_RUN_THE_PROGRAM	(0, "0"),
	
	PLAYER_1_MOVE_FORWARD		(NUMBER_OF_RUN_THE_PROGRAM.getLineNumber() + 1, "W"),
	PLAYER_1_MOVE_BACKWARD		(PLAYER_1_MOVE_FORWARD.getLineNumber() + 1, 	"S"),
	PLAYER_1_MOVE_LEFT			(PLAYER_1_MOVE_BACKWARD.getLineNumber() + 1, 	"A"),
	PLAYER_1_MOVE_RIGHT			(PLAYER_1_MOVE_LEFT.getLineNumber() + 1, 		"D"),
	PLAYER_1_MOVE_LEFT_UP		(PLAYER_1_MOVE_RIGHT.getLineNumber() + 1, 		"Q"),
	PLAYER_1_MOVE_LEFT_DOWN		(PLAYER_1_MOVE_LEFT_UP.getLineNumber() + 1, 	"Z"),
	PLAYER_1_MOVE_RIGHT_UP		(PLAYER_1_MOVE_LEFT_DOWN.getLineNumber() + 1, 	"E"),
	PLAYER_1_MOVE_RIGHT_DOWN	(PLAYER_1_MOVE_RIGHT_UP.getLineNumber() + 1, 	"C"),
	PLAYER_1_LEFT_JAB			(PLAYER_1_MOVE_RIGHT_DOWN.getLineNumber() + 1, 	"R"),
	PLAYER_1_RIGHT_JAB			(PLAYER_1_LEFT_JAB.getLineNumber() + 1, 		"T"),
	PLAYER_1_LEFT_HOOK			(PLAYER_1_RIGHT_JAB.getLineNumber() + 1, 		"F"),
	PLAYER_1_RIGHT_HOOK			(PLAYER_1_LEFT_HOOK.getLineNumber() + 1, 		"G"),
	PLAYER_1_LEFT_UPPERCUT		(PLAYER_1_RIGHT_HOOK.getLineNumber() + 1, 		"V"),
	PLAYER_1_RIGHT_UPPERCUT		(PLAYER_1_LEFT_UPPERCUT.getLineNumber() + 1, 	"B"),
	
	PLAYER_2_MOVE_FORWARD		(PLAYER_1_RIGHT_UPPERCUT.getLineNumber() + 1, 	"NUMPAD8"),
	PLAYER_2_MOVE_BACKWARD		(PLAYER_2_MOVE_FORWARD.getLineNumber() + 1, 	"NUMPAD2"),
	PLAYER_2_MOVE_LEFT			(PLAYER_2_MOVE_BACKWARD.getLineNumber() + 1, 	"NUMPAD4"),
	PLAYER_2_MOVE_RIGHT			(PLAYER_2_MOVE_LEFT.getLineNumber() + 1, 		"NUMPAD6"),
	PLAYER_2_MOVE_LEFT_UP		(PLAYER_2_MOVE_RIGHT.getLineNumber() + 1, 		"NUMPAD7"),
	PLAYER_2_MOVE_LEFT_DOWN		(PLAYER_2_MOVE_LEFT_UP.getLineNumber() + 1, 	"NUMPAD1"),
	PLAYER_2_MOVE_RIGHT_UP		(PLAYER_2_MOVE_LEFT_DOWN.getLineNumber() + 1, 	"NUMPAD9"),
	PLAYER_2_MOVE_RIGHT_DOWN	(PLAYER_2_MOVE_RIGHT_UP.getLineNumber() + 1, 	"NUMPAD3"),
	PLAYER_2_LEFT_JAB			(PLAYER_2_MOVE_RIGHT_DOWN.getLineNumber() + 1, 	"O"),
	PLAYER_2_RIGHT_JAB			(PLAYER_2_LEFT_JAB.getLineNumber() + 1, 		"P"),
	PLAYER_2_LEFT_HOOK			(PLAYER_2_RIGHT_JAB.getLineNumber() + 1, 		"K"),
	PLAYER_2_RIGHT_HOOK			(PLAYER_2_LEFT_HOOK.getLineNumber() + 1, 		"L"),
	PLAYER_2_LEFT_UPPERCUT		(PLAYER_2_RIGHT_HOOK.getLineNumber() + 1, 		"N"),
	PLAYER_2_RIGHT_UPPERCUT		(PLAYER_2_LEFT_UPPERCUT.getLineNumber() + 1, 	"M");
	
	// TODO ustawienia dla pada
	
	
	private final Integer 	lineNumber;
	private final String 	defaultValue;
	
	private DefaultControlSettings(Integer lineNumber, String defaultValue) 	{ 
		this.lineNumber = lineNumber;
		this.defaultValue = defaultValue;
	}
	
	public Integer 	getLineNumber() 		{ return lineNumber; }
	
	public String 	getDefaultValue()		{ return defaultValue; }
}
