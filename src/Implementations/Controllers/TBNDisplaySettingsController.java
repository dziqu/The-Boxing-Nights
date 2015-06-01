package Implementations.Controllers;

import Implementations.Models.TBNDisplaySettingsModel;
import Implementations.Others.IO;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.RadioButton;
import de.lessvoid.nifty.controls.RadioButtonStateChangedEvent;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class TBNDisplaySettingsController extends AbstractAppState implements ScreenController {
	// TODO: <screen id="ustawieniaWyswietlania" controller="PakietyPodstawowe.Kontroler.KontrolerUstawie�Wy�wietlania">
	private SimpleApplication app;
	private AppStateManager stateManager;
	private TBNDisplaySettingsModel displaySettingsModel;
	private Nifty nifty;
	private Screen screen;

	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		try {
			super.initialize(stateManager, app);
			this.displaySettingsModel = new TBNDisplaySettingsModel();
			this.app = (SimpleApplication) app;
			this.setStateManager(this.app.getStateManager());
			displaySettingsModel.initObjects(this.app);
			displaySettingsModel.showMainMenu(this);
			
//			TODO: zaznaczenie radio buttona
//			Element element = screen.findElementByName("GRadioButton1");
//			element.getNiftyControl(RadioButton.class).select();
//			<screen id="ustawieniaWyswietlania" controller="PakietyPodstawowe.Kontroler.KontrolerUstawie�Wy�wietlania">
		} catch (Exception ex) { ex.printStackTrace(); }
	}
	
	@NiftyEventSubscriber(pattern="GRadioButton.*")
	public void setSelected(final String id, final RadioButtonStateChangedEvent event) {
		IO.printL(event.getRadioButton().getElement().getId());
		Element element = screen.findElementByName("GRadioButton1");
		element.getNiftyControl(RadioButton.class).select();
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {
		this.setNifty(nifty);
		this.screen = screen;
	}

	@Override
	public void onEndScreen() { }

	@Override
	public void onStartScreen() { }

	public AppStateManager getStateManager() {
		return stateManager;
	}

	public void setStateManager(AppStateManager stateManager) {
		this.stateManager = stateManager;
	}

	public Nifty getNifty() {
		return nifty;
	}

	public void setNifty(Nifty nifty) {
		this.nifty = nifty;
	}

}
