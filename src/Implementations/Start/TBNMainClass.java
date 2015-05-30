package Implementations.Start;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Enums.DefaultControlSettings;
import Enums.Names;
import Implementations.Controllers.TBNMainMenuController;
import Implementations.Others.TBNDisplaySettings;
import Implementations.Others.IO;
import Interfaces.Collator;
import Interfaces.MainClass;

import com.jme3.app.SimpleApplication;

public final class TBNMainClass extends SimpleApplication implements MainClass {
	
	private TBNDisplaySettings displaySettingsInstance;
	private static TBNMainClass app;
	private static List <String> dataList = null;
	private static int numberOfRunOfTheProgram = 0;

    public static void main(String[] args) {
        app = new TBNMainClass();
        app.displaySettingsInstance = new TBNDisplaySettings(app);
        
        app.initControlSettings();
        app.loadCurrentDisplaySettings();
        app.setDisplaySettings();
        isDisplayViewActive(true);
        
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
    	stateManager.attach(new TBNMainMenuController());
        setDragToRotate(true);
    }
    
    @Override
    public void initControlSettings() {
    	Boolean isFileOfTheSettingsExist = checkWhetherFileOfTheControlSettingsExist();
        if (!isFileOfTheSettingsExist) createFileOfTheControlSettings();
        
        dataList = getDataListFromSettingsFile();
        
        int dataSize =  dataList.size();
        Boolean dataSizeIsEqualToZero = Collator.collateTo(dataSize, 0) == 0;
        if (dataSizeIsEqualToZero) makeFirstElementOfDataListZero();
        
        numberOfRunOfTheProgram = getNumberOfRunOfTheProgram();
        incrementNumberOfRunOfTheProgram();
        setNumberOfRunOfTheProgramInDataList();
        Boolean programWasRunngingForTheFirstTime = Collator.collateTo(numberOfRunOfTheProgram, 1) == 0;
        if(programWasRunngingForTheFirstTime) {
        	app.initDefaultDisplaySettings();
        	app.saveDefaultDisplaySettings();
        	app.saveTempDisplaySettings();
        	appendDefaultControl();
        }
        
        saveDataList();
    }
    
    private static Boolean checkWhetherFileOfTheControlSettingsExist() {
    	Boolean plikUstawieńIstnieje = IO.isFileExists(new File(Names.SETTINGS_DIRECTORY.getLocation()));
    	return plikUstawieńIstnieje;
    }
    
    private static void createFileOfTheControlSettings() {
    	IO.print(new File(Names.SETTINGS_DIRECTORY.getLocation()));
    }
    
    private static List <String> getDataListFromSettingsFile() {
    	return IO.scan(new File(Names.SETTINGS_DIRECTORY.getLocation()));
    }
    
    private static void makeFirstElementOfDataListZero() {
    	dataList.add("0");
    }
    
    private static int getNumberOfRunOfTheProgram() {
    	return Integer.parseInt(dataList.get(DefaultControlSettings.NUMBER_OF_RUN_THE_PROGRAM.getLineNumber()));
    }
    
    private static void incrementNumberOfRunOfTheProgram() {
    	numberOfRunOfTheProgram++;
    }
    
    private static void setNumberOfRunOfTheProgramInDataList() {
    	dataList.set(DefaultControlSettings.NUMBER_OF_RUN_THE_PROGRAM.getLineNumber(), String.valueOf(numberOfRunOfTheProgram));
    }
    
    private void initDefaultDisplaySettings() {
    	displaySettingsInstance.getDefaultSettings();
    }
    
    private void saveDefaultDisplaySettings() {
    	displaySettingsInstance.saveDefaultSettings();
    }
    
    private void saveTempDisplaySettings() {
    	displaySettingsInstance.saveDefaultSettingsAsTempSettings();
    }
    
    private static void appendDefaultControl() {
    	List <String> defaultControlSettingsList = getListOfDefaultControlSettings();
    	dataList = IO.append(dataList, defaultControlSettingsList);
    }
    
    private static void saveDataList() {
    	IO.printL(new File (Names.SETTINGS_DIRECTORY.getLocation()), dataList);
    }
    
    private void loadCurrentDisplaySettings() {
    	displaySettingsInstance.loadTempDatas();
    }
    
    private void setDisplaySettings() {
    	displaySettingsInstance.setDisplaySettings();
    }
    
    private static void isDisplayViewActive(boolean isDisplayViewActive) {
    	app.setShowSettings(isDisplayViewActive);
    }
    
    private static List <String> getListOfDefaultControlSettings() {
    	List <String> list = new ArrayList<String>();
    	list.add(DefaultControlSettings.PLAYER_1_MOVE_FORWARD.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_1_MOVE_BACKWARD.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_1_MOVE_LEFT.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_1_MOVE_RIGHT.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_1_MOVE_LEFT_UP.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_1_MOVE_LEFT_DOWN.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_1_MOVE_RIGHT_UP.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_1_MOVE_RIGHT_DOWN.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_1_LEFT_JAB.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_1_RIGHT_JAB.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_1_LEFT_HOOK.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_1_RIGHT_HOOK.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_1_LEFT_UPPERCUT.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_1_RIGHT_UPPERCUT.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_MOVE_FORWARD.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_MOVE_BACKWARD.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_MOVE_LEFT.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_MOVE_RIGHT.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_MOVE_LEFT_UP.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_MOVE_LEFT_DOWN.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_MOVE_RIGHT_UP.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_MOVE_RIGHT_DOWN.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_LEFT_JAB.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_RIGHT_JAB.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_LEFT_HOOK.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_RIGHT_HOOK.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_LEFT_UPPERCUT.getDefaultValue());
    	list.add(DefaultControlSettings.PLAYER_2_RIGHT_UPPERCUT.getDefaultValue());
    	
    	return list;
    }

    public void setDragToRotate(boolean cursorWillBeVisible) {
        flyCam.setDragToRotate(cursorWillBeVisible);
    }
}
