package Implementations.Controllers;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;

import Interfaces.PlayerComputerGameModel;

public class TBNPlayerComputerGameModel extends AbstractAppState
										implements PlayerComputerGameModel{
	
	private final SimpleApplication app;
	private final TBNPlayerComputerGameController tbnPlayerComputerGameController;

	public TBNPlayerComputerGameModel(final SimpleApplication app,
			final TBNPlayerComputerGameController tbnPlayerComputerGameController) {
		this.app = app;
		this.tbnPlayerComputerGameController = tbnPlayerComputerGameController;
	}

	@Override
	public void loadGame() {
		
	}

}
