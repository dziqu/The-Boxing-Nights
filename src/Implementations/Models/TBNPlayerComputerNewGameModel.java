package Implementations.Models;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Node;
import com.jme3.scene.plugins.blender.BlenderModelLoader;

import Implementations.Controllers.TBNPlayerComputerNewGameController;
import jme3test.bullet.PhysicsTestHelper;

public class TBNPlayerComputerNewGameModel {

	private SimpleApplication app;
	private Node rootNode;
	private AssetManager assetManager;
	private BulletAppState bulletAppState;
	private AppStateManager stateManager;
	private TBNPlayerComputerNewGameController tbnPlayerComputerNewGameController;
	private Node ring;

	public TBNPlayerComputerNewGameModel(Application app,
			TBNPlayerComputerNewGameController tbnPlayerComputerNewGameController) {
		this.app = (SimpleApplication) app;
		rootNode = this.app.getRootNode();
		assetManager = this.app.getAssetManager();
		stateManager = this.app.getStateManager();
		this.tbnPlayerComputerNewGameController = tbnPlayerComputerNewGameController;
		bulletAppState = new BulletAppState();
		stateManager.attach(bulletAppState);
	}

	public void loadGame() {
		PhysicsTestHelper.createPhysicsTestWorld(rootNode, assetManager, bulletAppState.getPhysicsSpace());
		
		assetManager.registerLoader(BlenderModelLoader.class, "blend");
		ring = (Node) assetManager.loadModel("Assets/Models/ring/ring.blend");
		ring.addControl(new RigidBodyControl(0f));
		rootNode.attachChild(ring);
		bulletAppState.getPhysicsSpace().addAll(ring);
	}

}
