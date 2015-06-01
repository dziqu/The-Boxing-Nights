package Implementations.Models;

import org.bushe.swing.event.EventServiceExistsException;
import org.bushe.swing.event.EventServiceLocator;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.input.InputManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;

import Enums.Names;
import Implementations.Controllers.TBNPlayerComputerMenuGameController;
import Interfaces.PlayerComputerGameModel;
import de.lessvoid.nifty.Nifty;

public class TBNPlayerComputerMenuGameModel extends AbstractAppState implements PlayerComputerGameModel {

	private final SimpleApplication app;
	private final TBNPlayerComputerMenuGameController tbnPlayerComputerGameController;
	private AssetManager assetManager;
	private InputManager inputManager;
	private AudioRenderer audioRenderer;
	private ViewPort viewPort;
	private ViewPort guiViewPort;

	public TBNPlayerComputerMenuGameModel(final SimpleApplication app,
			final TBNPlayerComputerMenuGameController tbnPlayerComputerGameController) {
		this.app = app;
		this.tbnPlayerComputerGameController = tbnPlayerComputerGameController;
		assetManager = this.app.getAssetManager();
		inputManager = this.app.getInputManager();
		audioRenderer = this.app.getAudioRenderer();
		viewPort = this.app.getViewPort();
		guiViewPort = this.app.getGuiViewPort();
	}

	@Override
	public void loadGame() {
		try {
			EventServiceLocator.setEventService("NiftyEventBus", null);
			NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, viewPort);
			Nifty nifty = niftyDisplay.getNifty();
			nifty.fromXml(Names.PATH_TO_PLAYER_COMPUTER_VIEW_DIRECTORY.getLocation(),
					Names.PLAYER_COMPUTER_VIEW_NAME.getLocation(), tbnPlayerComputerGameController);
			guiViewPort.addProcessor(niftyDisplay);
		} catch (EventServiceExistsException ex) {
		}
	}

}
