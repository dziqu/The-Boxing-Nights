package Implementations.Controllers;

import Implementations.Models.TBNMainMenuModel;
import Interfaces.MainMenuController;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public final class TBNMainMenuController extends AbstractAppState implements ScreenController, MainMenuController {

	private SimpleApplication 		app;
	private AppStateManager 		stateManager;
	private TBNMainMenuModel 		mainMenuModel;
	private Nifty 					nifty;
	private Screen 					screen;

	@Override
	public void initialize(final AppStateManager stateManager, final Application app) {
		try {
			super.initialize(stateManager, app);
			this.app 			= 	(SimpleApplication) app;
			this.stateManager 	= 	this.app.getStateManager();
			this.mainMenuModel 	= 	new TBNMainMenuModel(this.app, this);
			
			mainMenuModel.loadMenu();
		} catch (Exception ex) {
			ex.printStackTrace();
		} }
	
	@Override
	public void newGame() {
		if (nifty != null) nifty.exit();
		stateManager.attach(new TBNNewGameMenuController()); }
	
	@Override
	public void loadGame() { }
	
	@Override
	public void multiplayer() { }
	
	@Override
	public void settings() {
		if (nifty != null) nifty.exit();
		stateManager.attach(new TBNSettingsController()); }
	
	@Override
	public void aboutTheGame() { }
	
	@Override
	public void exit() { app.stop(); }
	
	@Override
	public void bind(Nifty nifty, Screen screen) {
		this.nifty = nifty;
		this.screen = screen; }
	
	@Override
	public void onStartScreen() { }
	
	@Override
	public void onEndScreen() { }

}
