package Implementations.Controllers;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;

import Implementations.Models.TBNPlayerComputerMenuGameModel;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class TBNPlayerComputerMenuGameController extends AbstractAppState implements ScreenController {
	
	private SimpleApplication app;
	private AppStateManager stateManager;
	private Nifty nifty;
	private Screen screen;
	private TBNPlayerComputerMenuGameModel tbnPlayerComputerMenuGameModel;
	private Node player;
	private static float counter = 0;

	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		try {
			super.initialize(stateManager, app);
			this.app = (SimpleApplication) app;
			this.stateManager = this.app.getStateManager();
			tbnPlayerComputerMenuGameModel = new TBNPlayerComputerMenuGameModel(this.app, this);
			tbnPlayerComputerMenuGameModel.loadGame();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void ok() {
		
	}
	
	public void cancel() {
		if (nifty != null) nifty.exit();
		stateManager.attach(new TBNNewGameMenuController());
	}
	
	@Override
	public void update(float tpf) {
		
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {
		this.nifty = nifty;
		this.screen = screen;
	}

	@Override
	public void onEndScreen() {
	}

	@Override
	public void onStartScreen() {
	}

}
