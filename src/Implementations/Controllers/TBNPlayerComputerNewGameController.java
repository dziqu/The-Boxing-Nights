package Implementations.Controllers;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

import Implementations.Models.TBNPlayerComputerNewGameModel;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class TBNPlayerComputerNewGameController extends AbstractAppState implements ScreenController {

	private Nifty nifty;
	private Screen screen;
	private SimpleApplication app;
	private TBNPlayerComputerNewGameModel tbnPlayerComputerNewGameModel;
	
	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		try {
			super.initialize(stateManager, app);
			this.app = (SimpleApplication) app;
			tbnPlayerComputerNewGameModel = new TBNPlayerComputerNewGameModel(this.app, this);
//			tbnPlayerComputerNewGameModel.loadGame();
			stateManager.attach(tbnPlayerComputerNewGameModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {
		this.nifty=nifty;
		this.screen = screen;
	}

	@Override
	public void onEndScreen() { }

	@Override
	public void onStartScreen() { }

	

}
