package Implementations.Models;

import java.util.ArrayList;
import java.util.List;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.ChaseCamera;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl.ControlDirection;
import com.jme3.scene.plugins.blender.BlenderModelLoader;

import Implementations.Controllers.TBNPlayerComputerNewGameController;
import Implementations.Others.IO;

public class TBNPlayerComputerNewGameModel extends AbstractAppState implements ActionListener, AnimEventListener {

	private SimpleApplication app;
	private Node rootNode;
	private AssetManager assetManager;
	private BulletAppState bulletAppState;
	private AppStateManager stateManager;
	private TBNPlayerComputerNewGameController tbnPlayerComputerNewGameController;
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
	private AnimControl animationControl;
	private AnimChannel animationChannel;
	private ViewPort viewPort;
	private Node player1;
	private RigidBodyControl ringControl;
	private Node ring;
	private BetterCharacterControl player1Control;
	private ChaseCamera chaseCam;
	private Node player2;
	private BetterCharacterControl player2Control;
	private float player2x = 0;
	private float player2y = 1;
	private float player2z = 0;
	private float player1x = 0;
	private float player1y = 1;
	private float player1z = 0;
	private CameraNode camNode;
	private Node node2;
	private Node node4;

	public TBNPlayerComputerNewGameModel(Application app,
			TBNPlayerComputerNewGameController tbnPlayerComputerNewGameController) {
		this.app = (SimpleApplication) app;
		rootNode = this.app.getRootNode();
		assetManager = this.app.getAssetManager();
		stateManager = this.app.getStateManager();
		inputManager = this.app.getInputManager();
		flyCam = this.app.getFlyByCamera();
		cam = this.app.getCamera();
		viewPort = this.app.getViewPort();
		this.tbnPlayerComputerNewGameController = tbnPlayerComputerNewGameController;
		bulletAppState = new BulletAppState();
		stateManager.attach(bulletAppState);
		flyCam.setMoveSpeed(50f);
		
		loadGame();
	}

	public void loadGame() {
		
		DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(cam.getDirection());
        rootNode.addLight(sun);
        
        assetManager.registerLoader(BlenderModelLoader.class, "blend");
        
        ring = (Node) assetManager.loadModel("Assets/Models/ring/ring.blend");
        ringControl = new RigidBodyControl(0f);
        ring.addControl(ringControl);
        bulletAppState.getPhysicsSpace().add(ringControl);
        rootNode.attachChild(ring);
        

        player1 = (Node) assetManager.loadModel("Assets/Models/player/player.blend");
        player1.setLocalScale(3f);
        player1.setLocalTranslation(9, 1, -9);
        player1.rotate(new Quaternion().fromAngles(50,50,50));
        player1Control = new BetterCharacterControl(0.3f, 2.5f, 8f);
        player1Control.setJumpForce(new Vector3f(30f, 300f, 3f));
        player1.addControl(player1Control);
        bulletAppState.getPhysicsSpace().add(player1Control);
        rootNode.attachChild(player1);
        
        player2 = (Node) assetManager.loadModel("Assets/Models/player/player.blend");
        player2.setLocalScale(3f);
        player2.setLocalTranslation(-2, 1, 3);
        player2.rotate(new Quaternion().fromAngles(50,50,50));
        player2Control = new BetterCharacterControl(0.3f, 2.5f, 8f);
        player2Control.setJumpForce(new Vector3f(30f, 300f, 3f));
        player2.addControl(player2Control);
        bulletAppState.getPhysicsSpace().add(player2Control);
        rootNode.attachChild(player2);
		
        chaseCam = new ChaseCamera(cam, player1, inputManager);
//        TODO: camera
//        cam.setRotation(new Quaternion().fromAngles(180, 0, 0));
//        camNode = new CameraNode("CamNode", cam);
//        camNode.setControlDir(ControlDirection.SpatialToCamera);
//        player1.attachChild(camNode);
//        camNode.setLocalTranslation(new Vector3f(550, 350, -30));
//        camNode.lookAt(player1.getLocalTranslation(), Vector3f.UNIT_Y);
//        flyCam.setEnabled(false);
        
        
		// TODO: łączyć spodnie z meshem
		Node node1 = (Node) player1.getChild("RootNode");
		node2 = (Node) player1.getChild("Genesis2Male-skinInstance");
		Node node3 = (Node) player2.getChild("RootNode");
		node4 = (Node) player2.getChild("Genesis2Male-skinInstance");
		node2.lookAt(new Vector3f(0,-35,0), node4.getWorldTranslation());
		
		IO.printL(node3.getChildren());
		animationControl = node2.getControl(AnimControl.class);
//		IO.printL(animationControl);
		
		animationControl.addListener(this);
		animationChannel = animationControl.createChannel();
		animationChannel.setAnim("garda");
		List animations = new ArrayList(animationControl.getAnimationNames());
		IO.printL(animations);

		inputManager.addMapping("CharLeft", new KeyTrigger(KeyInput.KEY_A));
		inputManager.addMapping("CharRight", new KeyTrigger(KeyInput.KEY_D));
		inputManager.addMapping("CharForward", new KeyTrigger(KeyInput.KEY_W));
		inputManager.addMapping("CharBackward", new KeyTrigger(KeyInput.KEY_S));
		inputManager.addMapping("CharJump", new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addListener(this, "CharLeft", "CharRight");
		inputManager.addListener(this, "CharForward", "CharBackward");
		inputManager.addListener(this, "CharJump");
	}
	
	@Override
	public void update(float tpf) {
//		node2.lookAt(new Vector3f(0,-35,0), node4.getWorldTranslation());
//		node4.lookAt(new Vector3f(0,-35,0), node2.getWorldTranslation());
	}

	@Override
	public void onAction(String binding, boolean value, float tpf) {
		if (binding.equals("CharLeft")) {
			if (value){
				player2Control.setWalkDirection(new Vector3f(1, 0, 2));
				left = true;
			}
			else
				left = false;
		} else if (binding.equals("CharRight")) {
			if (value) {
				right = true;
				player2Control.setWalkDirection(new Vector3f(-1, 0, -2));
			}
			else
				right = false;
		} else if (binding.equals("CharForward")) {
			if (value) {
				up = true;
				player1Control.setWalkDirection(player2.getLocalTranslation());
			}
			else
				up = false;
		} else if (binding.equals("CharBackward")) {
			if (value) {
				down = true;
				player1Control.setWalkDirection(player2.getLocalTranslation().negate());
			}
			else
				down = false;
		} else if (binding.equals("CharJump")) {
			player1Control.setWalkDirection(player1.getLocalTranslation());
		}
	}

	@Override
	public void onAnimChange(AnimControl arg0, AnimChannel arg1, String arg2) {

	}

	@Override
	public void onAnimCycleDone(AnimControl arg0, AnimChannel arg1, String arg2) {

	}

}
