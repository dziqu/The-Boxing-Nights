package Implementations.Models;

import org.bushe.swing.event.EventServiceExistsException;
import org.bushe.swing.event.EventServiceLocator;

import Enums.Names;
import Implementations.Controllers.TBNDisplaySettingsController;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.input.InputManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;

import de.lessvoid.nifty.Nifty;

public class TBNDisplaySettingsModel extends AbstractAppState {
	private SimpleApplication app;
    private ViewPort viewPort;
    private AssetManager assetManager;
    private InputManager inputManager;
    private AudioRenderer audioRenderer;
    private ViewPort guiViewPort;
    private AppStateManager stateManager;
    private Nifty nifty;
    
    public void initObjects(Application app) {
        this.app = (SimpleApplication) app;
        this.viewPort = this.app.getViewPort();
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();
        this.audioRenderer = this.app.getAudioRenderer();
        this.guiViewPort = this.app.getGuiViewPort();
        this.stateManager = this.app.getStateManager();
    }
    
    public void showMainMenu(TBNDisplaySettingsController kontrolerUstawieńWyświetlania) {
        try {
			EventServiceLocator.setEventService("NiftyEventBus", null);
			NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, viewPort);
	        nifty = niftyDisplay.getNifty();
	        nifty.fromXml(Names.PATH_TO_DISPLAY_SETTINGS_VIEW_DIRECTORY.getLocation(), Names.DISPLAY_SETTINGS_MENU_VIEW_NAME.getLocation(), kontrolerUstawieńWyświetlania);
	        stateManager.attach(kontrolerUstawieńWyświetlania);
	        guiViewPort.addProcessor(niftyDisplay);
		} catch (EventServiceExistsException ex) {
		}
    }
}
