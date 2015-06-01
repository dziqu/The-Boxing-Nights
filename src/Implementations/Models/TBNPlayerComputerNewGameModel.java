package Implementations.Models;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.plugins.blender.BlenderModelLoader;

import Implementations.Controllers.TBNPlayerComputerNewGameController;

public class TBNPlayerComputerNewGameModel implements ActionListener {

	private SimpleApplication app;
	private Node rootNode;
	private AssetManager assetManager;
	private BulletAppState bulletAppState;
	private AppStateManager stateManager;
	private TBNPlayerComputerNewGameController tbnPlayerComputerNewGameController;
	private Node ring;
	private Node player1;
	private CapsuleCollisionShape capsuleCollisionShape;
	private BetterCharacterControl character;
	private RigidBodyControl landscape;
	private CharacterControl playerPhysics;
	private InputManager inputManager;
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private FlyByCamera flyCam;
	private Camera cam;

	public TBNPlayerComputerNewGameModel(Application app,
			TBNPlayerComputerNewGameController tbnPlayerComputerNewGameController) {
		this.app = (SimpleApplication) app;
		rootNode = this.app.getRootNode();
		assetManager = this.app.getAssetManager();
		stateManager = this.app.getStateManager();
		inputManager = this.app.getInputManager();
		flyCam = this.app.getFlyByCamera();
		cam = this.app.getCamera();
		this.tbnPlayerComputerNewGameController = tbnPlayerComputerNewGameController;
		bulletAppState = new BulletAppState();
		stateManager.attach(bulletAppState);
		flyCam.setMoveSpeed(50f);
	}

	public void loadGame() {
//		PhysicsTestHelper.createPhysicsTestWorld(rootNode, assetManager,
//				bulletAppState.getPhysicsSpace());
		
		assetManager.registerLoader(BlenderModelLoader.class, "blend");
		ring = (Node) assetManager.loadModel("Assets/Models/ring/ringBezBokow.blend");
		ring.setName("ring");
		rootNode.attachChild(ring);
		RigidBodyControl phy = new RigidBodyControl(0f);
		ring.addControl(phy);
		bulletAppState.getPhysicsSpace().add(phy);
		
		cam.setLocation(new Vector3f(0f, 5f, 20f));
		
		player1 = (Node) assetManager.loadModel("Assets/Models/player/player.blend");
		player1.setLocalScale(3f);
		rootNode.attachChild(player1);
		CapsuleCollisionShape capsule = new CapsuleCollisionShape(1f, 1f);
		character = new BetterCharacterControl(0.3f, 2.5f, 8f);
		character.setJumpForce(new Vector3f(30f, 300f, 3f));
		player1.addControl(character);
		bulletAppState.getPhysicsSpace().add(character);
		
		
		inputManager.addMapping("CharLeft", new KeyTrigger(KeyInput.KEY_A));
		inputManager.addMapping("CharRight", new KeyTrigger(KeyInput.KEY_D));
		inputManager.addMapping("CharForward", new KeyTrigger(KeyInput.KEY_W));
		inputManager.addMapping("CharBackward", new KeyTrigger(KeyInput.KEY_S));
		inputManager
				.addMapping("CharJump", new KeyTrigger(KeyInput.KEY_RETURN));
		inputManager.addMapping("CharAttack",
				new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addListener(this, "CharLeft", "CharRight");
		inputManager.addListener(this, "CharForward", "CharBackward");
		inputManager.addListener(this, "CharJump", "CharAttack");
	}

	@Override
	public void onAction(String binding, boolean value, float tpf) {
		if (binding.equals("CharLeft")) {
			if (value)
				left = true;
			else
				left = false;
		} else if (binding.equals("CharRight")) {
			if (value)
				right = true;	
			else
				right = false;
		} else if (binding.equals("CharForward")) {
			if (value)
				up = true;
			else
				up = false;
		} else if (binding.equals("CharBackward")) {
			if (value)
				down = true;
			else
				down = false;
		} else if (binding.equals("CharJump"))
			character.jump();
		
		if (binding.equals("CharAttack")) {
			character.jump();
		}
	}

}
