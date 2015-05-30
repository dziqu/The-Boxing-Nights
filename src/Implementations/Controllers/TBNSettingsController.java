package Implementations.Controllers;

import Implementations.Models.TBNSettingsModel;
import Interfaces.SettingsController;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public final class TBNSettingsController extends AbstractAppState implements ScreenController, SettingsController {
	
	private TBNSettingsModel modelUstawień;
	private SimpleApplication app;
	private AppStateManager stateManager;
	private Nifty nifty;
	@SuppressWarnings("unused")
	private Screen screen;
    
    @Override
	public void initialize(AppStateManager stateManager, Application app) {
    	super.initialize(stateManager, app);
    	this.app = (SimpleApplication) app;
    	this.stateManager = this.app.getStateManager();
    	this.modelUstawień = new TBNSettingsModel();
    	
    	modelUstawień.inicjujObiekty(this.app);
    	modelUstawień.wyświetlMenuUstawień(this);
    }
    
    @Override
	public void displaySettings() {
		if (nifty != null) nifty.exit();
    	stateManager.attach(new TBNDisplaySettingsController());
	}

	@Override
	public void controlSettings() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancel() {
		if (nifty != null) nifty.exit();
    	stateManager.attach(new TBNMainMenuController());
	}
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
		this.nifty = nifty;
		this.screen = screen; }

	@Override
	public void onEndScreen() { }

	@Override
	public void onStartScreen() { }

	

}
