package Enums;

public enum Names {
	
	ROOT_DIRECTORY									("src/"),
	SETTINGS_PACKAGE								(ROOT_DIRECTORY.getLocation() 	+ 	"Settings/"),
	SETTINGS_DIRECTORY								(SETTINGS_PACKAGE.getLocation() + 	"settings.txt"),
	DEFAULT_SETTINGS_DIRECTORY						("TBNUstDefault"),
	TEMPORARY_SETTINGS_DIRECTORY					("TBNUstTemp"),
	
	ASSETS_PACKAGE									("Assets/"),
	VIEW_PACKAGE									(ASSETS_PACKAGE.getLocation() 	+ 	"Interface/"),
	PATH_TO_NEW_GAME_MENU_DIRECTORY					(VIEW_PACKAGE.getLocation() 	+ 	"newGameMenu.xml"),
	PATH_TO_MAIN_MENU_DIRECTORY						(VIEW_PACKAGE.getLocation() 	+  	"mainMenu.xml"),
	PATH_TO_SETTINGS_VIEW_DIRECTORY					(VIEW_PACKAGE.getLocation() 	+ 	"settings.xml"),
	PATH_TO_DISPLAY_SETTINGS_VIEW_DIRECTORY			(VIEW_PACKAGE.getLocation() 	+ 	"displaySettings.xml"),
	PATH_TO_PLAYER_COMPUTER_VIEW_DIRECTORY			(VIEW_PACKAGE.getLocation()		+	"playerComputerMenu.xml"),
	
	NEW_GAME_VIEW_NAME								("newGameMenu"),
	MAIN_MENU_VIEW_NAME								("mainMenu"),
	SETTINGS_MENU_VIEW_NAME							("settings"),
	PLAYER_COMPUTER_VIEW_NAME						("playerComputerMenu"),
	DISPLAY_SETTINGS_MENU_VIEW_NAME					("displaySettings");
    
    private final String location;
    
    private Names(String location) 					{ this.location = location; }
    
    public String getLocation() 					{ return location; }

}
