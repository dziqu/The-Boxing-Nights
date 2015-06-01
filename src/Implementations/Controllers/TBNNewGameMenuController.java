package Implementations.Controllers;

import Implementations.Models.TBNNewGameMenuModel;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 *
 * @author Filip
 */
public final class TBNNewGameMenuController extends AbstractAppState implements ScreenController {

	private TBNNewGameMenuModel newGameMenuModel;
	private SimpleApplication app;
	private AppStateManager stateManager;
	private Nifty nifty;
	@SuppressWarnings("unused")
	private Screen screen;

	@Override
	public void initialize(final AppStateManager stateManager, final Application app) {
		try {
			super.initialize(stateManager, app);
			this.app = (SimpleApplication) app;
			this.stateManager = this.app.getStateManager();
			this.newGameMenuModel = new TBNNewGameMenuModel(this.app, this);
			newGameMenuModel.loadMenu();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setPlayerComputerGame() {
		if (nifty != null) nifty.exit();
		stateManager.attach(new TBNPlayerComputerGameController());
	}
	
	public void setPlayerPlayerController() {
		if (nifty != null) nifty.exit();
		stateManager.attach(new TBNPlayerPlayerGameController());
	}
	
	public void cancel() {
		if (nifty != null) nifty.exit();
		stateManager.attach(new TBNMainMenuController());
	}

	public void bind(Nifty nifty, Screen screen) { 
		this.nifty = nifty;
		this.screen = screen;
	}

	public void onStartScreen() {

	}

	public void onEndScreen() {
	}

}
