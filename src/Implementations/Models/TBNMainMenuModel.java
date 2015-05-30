package Implementations.Models;

import org.bushe.swing.event.EventServiceExistsException;
import org.bushe.swing.event.EventServiceLocator;

import Enums.Names;
import Implementations.Controllers.TBNMainMenuController;
import Interfaces.MenuModel;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.input.InputManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;

import de.lessvoid.nifty.Nifty;

/**
 *
 * @author Filip
 */
public final class TBNMainMenuModel extends AbstractAppState implements MenuModel {

    private final SimpleApplication 	app;
    private final ViewPort 				viewPort;
    private final AssetManager 			assetManager;
    private final InputManager 			inputManager;
    private final AudioRenderer 		audioRenderer;
    private final ViewPort 				guiViewPort;
    private final TBNMainMenuController 	mainMenuController;
    
    public TBNMainMenuModel(final Application app, final TBNMainMenuController mainMenuController) {
    	this.app 				= 		(SimpleApplication) app;
        this.viewPort 			= 		this.app.getViewPort();
        this.assetManager 		= 		this.app.getAssetManager();
        this.inputManager 		= 		this.app.getInputManager();
        this.audioRenderer 		= 		this.app.getAudioRenderer();
        this.guiViewPort 		= 		this.app.getGuiViewPort();
        this.mainMenuController = 		mainMenuController;
    }
    
    @Override
    public void loadMenu() {
        try {
			EventServiceLocator.setEventService("NiftyEventBus", null);
			NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, viewPort);
	        Nifty nifty = niftyDisplay.getNifty();
	        nifty.fromXml(Names.PATH_TO_MAIN_MENU_DIRECTORY.getLocation(), Names.MAIN_MENU_VIEW_NAME.getLocation(), mainMenuController);
	        guiViewPort.addProcessor(niftyDisplay);
		} catch (EventServiceExistsException ex) {
		}
    }
}
