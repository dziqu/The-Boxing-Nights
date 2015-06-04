package Implementations.Models;

import java.util.ArrayList;
import java.util.HashSet;
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
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.ChaseCamera;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.plugins.blender.BlenderModelLoader;

import Implementations.Controllers.TBNPlayerComputerNewGameController;
import Implementations.Others.IO;
import jme3test.input.combomoves.ComboMove;
import jme3test.input.combomoves.ComboMoveExecution;

public class TBNPlayerComputerNewGameModel extends AbstractAppState implements
		ActionListener, AnimEventListener {

	private SimpleApplication app;
	private Node rootNode;
	private AssetManager assetManager;
	private BulletAppState bulletAppState;
	private AppStateManager stateManager;
	private TBNPlayerComputerNewGameController tbnPlayerComputerNewGameController;
	private InputManager inputManager;
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private FlyByCamera flyCam;
	private Camera cam;
	private AnimControl player1AnimationControl;
	private AnimChannel player1AnimationChannel;
	private ViewPort viewPort;
	private Node player1;
	private RigidBodyControl ringControl;
	private Node ring;
	private BetterCharacterControl player1Control;
	private ChaseCamera chaseCam;
	private Node player2;
	private BetterCharacterControl player2Control;
	private CameraNode camNode;
	private Node node2;
	private Node node4;
	private Vector3f walkDir;
	private HashSet<String> pressedMappings;
	private ComboMove upAndLeft;
	private ComboMoveExecution upAndLeftExec;
	private ComboMove currentMove = null;
	private float currentMoveCastTime = 0;
	private float time = 0;
	private ComboMove upAndRight;
	private ComboMoveExecution upAndRightExec;
	private ComboMove downAndLeft;
	private ComboMoveExecution downAndLeftExec;
	private ComboMove downAndRight;
	private ComboMoveExecution downAndRightExec;
	private AnimControl player2AnimationControl;
	private AnimChannel player2AnimationChannel;
	private final int leftKey = KeyInput.KEY_A;
	private final int rightKey = KeyInput.KEY_D;
	private final int upKey = KeyInput.KEY_W;
	private final int downKey = KeyInput.KEY_S;
	private final int spaceKey = KeyInput.KEY_SPACE;
	private final int leftHandKey = KeyInput.KEY_Q;
	private final int rightHandKey = KeyInput.KEY_E;
	private final String changePositionAnimationName = "changePosition";
	private final String guardAnimationName = "guard";
	private final String leftJabAnimationName = "leftJab";
	private final String leftKeyName = "left";
	private final String rightKeyName = "right";
	private final String upKeyName = "up";
	private final String downKeyName = "down";
	private final String spaceKeyName = "space";
	private final String leftHandKeyName = "leftHand";
	private final String rightHandKeyName = "rightHand";
	private final String upAndLeftComboName = "upAndLeft";
	private final String upAndRightComboName = "upAndRight";
	private final String downAndLeftComboName = "downAndLeft";
	private final String downAndRightComboName = "downAndRight";
	private final String leftJabComboName = "leftJabCombo";
	private final Vector3f upAndLeftVector = new Vector3f(-80, 0, 80);
	private final Vector3f upAndRightVector = new Vector3f(-80, 0, -80);
	private final Vector3f downAndLeftVector = new Vector3f(80, 0, 80);
	private final Vector3f downAndRightVector = new Vector3f(80, 0, -80);
	private final Vector3f upVectorName = new Vector3f(-4, 0, 0);
	private final Vector3f downVectorName = new Vector3f(4, 0, 0);
	private final Vector3f leftVectorName = new Vector3f(0, 0, 4);
	private final Vector3f rightVectorName = new Vector3f(0, 0, -4);
	private Node node1;
	private Node node3;
	private boolean space;
	private ComboMove leftJabCombo;
	private ComboMoveExecution leftJabComboExec;

	public TBNPlayerComputerNewGameModel(
			Application app,
			TBNPlayerComputerNewGameController tbnPlayerComputerNewGameController) {
		this.app 		= (SimpleApplication) app;
		rootNode		= this.app.getRootNode();
		assetManager 	= this.app.getAssetManager();
		stateManager 	= this.app.getStateManager();
		inputManager 	= this.app.getInputManager();
		flyCam 			= this.app.getFlyByCamera();
		cam 			= this.app.getCamera();
		viewPort 		= this.app.getViewPort();
		this.tbnPlayerComputerNewGameController 
						= tbnPlayerComputerNewGameController;
		bulletAppState 	= new BulletAppState();
		stateManager	.attach(bulletAppState);
		flyCam			.setMoveSpeed(50f);
		walkDir 		= new Vector3f();
		pressedMappings = new HashSet<String>();
		loadGame();
		assetManager	.registerLoader(BlenderModelLoader.class, "blend");
	}

	public void loadGame() {
		addDirectionalLight();
		initRingObject();
		initPlayer1();
		initPlayer2();
		initChaseCam();
		getPlayersNodes();
		initAnimationsControls();
		initAnimationsChannels();
		
		List animations = new ArrayList(
				player1AnimationControl.getAnimationNames());
		IO.printL(animations);

		if (player1AnimationChannel != null) {
			player1AnimationChannel.setAnim(guardAnimationName);
		}
		if (player2AnimationChannel != null) {
			player2AnimationChannel.setAnim(guardAnimationName);
		}
		
		initKeys();
		addListeners();
		initCombos();
	}
	
	private void addDirectionalLight() {
		DirectionalLight sun = new DirectionalLight();
		sun.setColor(ColorRGBA.White);
		sun.setDirection(cam.getDirection());
		rootNode.addLight(sun);
	}
	
	private void initRingObject() {
		ring = (Node) assetManager.loadModel("Assets/Models/ring/ring.blend");
		ringControl = new RigidBodyControl(0f);
		ring.addControl(ringControl);
		bulletAppState.getPhysicsSpace().add(ringControl);
		rootNode.attachChild(ring);
	}
	
	private void initPlayer1() {
		player1 = (Node) assetManager
				.loadModel("Assets/Models/player/player.blend");
		player1.setLocalScale(0.03f);
		player1.setLocalTranslation(9, 1, -9);
		player1Control = new BetterCharacterControl(0.3f, 2.5f, 8f);
		player1Control.setJumpForce(new Vector3f(30f, 300f, 3f));
		player1Control.setGravity(new Vector3f(0, -10, 0));
		player1.addControl(player1Control);
		bulletAppState.getPhysicsSpace().add(player1Control);
		rootNode.attachChild(player1);
	}
	
	private void initPlayer2() {
		player2 = (Node) assetManager
				.loadModel("Assets/Models/player/player.blend");
		player2.setLocalScale(0.03f);
		player2.setLocalTranslation(-2, 1, 3);
		player2Control = new BetterCharacterControl(0.3f, 2.5f, 8f);
		player2Control.setJumpForce(new Vector3f(30f, 300f, 3f));
		player2Control.setGravity(new Vector3f(0, -10, 0));
		player2.addControl(player2Control);
		bulletAppState.getPhysicsSpace().add(player2Control);
		rootNode.attachChild(player2);
	}
	
	private void initChaseCam() {
		chaseCam = new ChaseCamera(cam, player1, inputManager);
	}
	
	private void getPlayersNodes() {
		node1 = (Node) player1.getChild("RootNode");
		node2 = (Node) player1.getChild("Genesis2Male-skinInstance");
		node3 = (Node) player2.getChild("RootNode");
		node4 = (Node) player2.getChild("Genesis2Male-skinInstance");
		node2.lookAt(new Vector3f(0, -35, 0), node4.getWorldTranslation());
	}
	
	private void initAnimationsControls() {
		player1AnimationControl = node2.getControl(AnimControl.class);
		player2AnimationControl = node4.getControl(AnimControl.class);
		player1AnimationControl.addListener(this);
	}
	
	private void initAnimationsChannels() {
		player1AnimationChannel = player1AnimationControl.createChannel();
		player2AnimationChannel = player2AnimationControl.createChannel();
	}
	
	private void initKeys() {
		initKey(leftKeyName, leftKey);
		initKey(rightKeyName, rightKey);
		initKey(upKeyName, upKey);
		initKey(downKeyName, downKey);
		initKey(spaceKeyName, spaceKey);
		initKey(leftHandKeyName, leftHandKey);
	}
	
	private void addListeners() {
		addListener(this, leftKeyName, rightKeyName);
		addListener(this, upKeyName, downKeyName);
		addListener(this, spaceKeyName, leftHandKeyName);
	}
	
	private void initCombos() {
		initUpAndLeftCombo();
		initUpAndRightCombo();
		initDownAndLeftCombo();
		initDownAndRightCombo();
		initLeftJabCombo();
	}
	
	private void initKey(String keyName, int keyValue) {
		inputManager.addMapping(keyName, new KeyTrigger(keyValue));
	}
	
	private void addListener(InputListener inputListener, String ... keysNames) {
		inputManager.addListener(inputListener, keysNames);
	}
	
	private void initUpAndLeftCombo() {
		upAndLeft = new ComboMove(upAndLeftComboName);
		upAndLeft.press(spaceKeyName, upKeyName, leftKeyName).done();
		upAndLeft.setUseFinalState(false);
		upAndLeftExec = new ComboMoveExecution(upAndLeft);
	}
	
	private void initUpAndRightCombo() {
		upAndRight = new ComboMove(upAndRightComboName);
		upAndRight.press(spaceKeyName, upKeyName, rightKeyName).done();
		upAndRight.setUseFinalState(false);
		upAndRightExec = new ComboMoveExecution(upAndRight);
	}
	
	private void initDownAndLeftCombo() {
		downAndLeft = new ComboMove(downAndLeftComboName);
		downAndLeft.press(spaceKeyName, downKeyName, leftKeyName).done();
		downAndLeft.setUseFinalState(false);
		downAndLeftExec = new ComboMoveExecution(downAndLeft);
	}
	
	private void initDownAndRightCombo() {
		downAndRight = new ComboMove(downAndRightComboName);
		downAndRight.press(spaceKeyName, downKeyName, rightKeyName).done();
		downAndRight.setUseFinalState(false);
		downAndRightExec = new ComboMoveExecution(downAndRight);
	}
	
	private void initLeftJabCombo() {
		leftJabCombo = new ComboMove(leftJabComboName);
		leftJabCombo.press(spaceKeyName, upKeyName, leftHandKeyName).done();;
		leftJabCombo.setUseFinalState(false);
		leftJabComboExec = new ComboMoveExecution(leftJabCombo);
	}
	
	@Override
	public void update(float tpf) {
		walkDir.set(0, 0, 0);

		setCombosActions(tpf);
		setKeysActions();
		setWalkDirection(player1Control, walkDir);
		rotatePlayersNodes();
	}

	private void setCombosActions(float tpf) {
		time += tpf;
		upAndLeftExec.updateExpiration(time);
		upAndRightExec.updateExpiration(time);
		downAndLeftExec.updateExpiration(time);
		downAndRightExec.updateExpiration(time);
		leftJabComboExec.updateExpiration(time);

		if (currentMove != null) {
			currentMoveCastTime -= tpf;
			if (currentMoveCastTime <= 0) {
				String comboName = currentMove.getMoveName();
				Vector3f vectorToSet = null;
				String animationNameToSet = null;
				switch (comboName) {
					case upAndLeftComboName: {
						vectorToSet = upAndLeftVector;
						animationNameToSet = changePositionAnimationName;
						break;
					} case upAndRightComboName: {
						vectorToSet = upAndRightVector;
						animationNameToSet = changePositionAnimationName;
						break;
					} case downAndLeftComboName: {
						vectorToSet = downAndLeftVector;
						animationNameToSet = changePositionAnimationName;
						break;
					} case downAndRightComboName: {
						vectorToSet = downAndRightVector;
						animationNameToSet = changePositionAnimationName;
						break;
					} case leftJabComboName: {
						animationNameToSet = leftJabAnimationName;
						break;
					} default: {
						animationNameToSet = guardAnimationName;
						break;
					}	
				}
				
				if (vectorToSet != null) setVector(walkDir, vectorToSet);
				if (animationNameToSet != null) {
					setAnimation(player1AnimationChannel, animationNameToSet);
				}
				
				currentMoveCastTime = 0;
				currentMove = null;
			}
		}
	}

	private void setKeysActions() {
		if (!space) {
			Vector3f vectorToSetName = null;
			String animationToSetName = null;
			if (up) {
				vectorToSetName = upVectorName;
				animationToSetName = changePositionAnimationName;
			} else if (down) {
				vectorToSetName = downVectorName;
				animationToSetName = changePositionAnimationName;
			} else if (left) {
				vectorToSetName = leftVectorName;
				animationToSetName = changePositionAnimationName;
			} else if (right) {
				vectorToSetName = rightVectorName;
				animationToSetName = changePositionAnimationName;
			} else {
				animationToSetName = guardAnimationName;
			}
			
			if (vectorToSetName != null) {
				setVector(walkDir, vectorToSetName);
			}
			if (animationToSetName != null) {
				setAnimation(player1AnimationChannel, animationToSetName); 
			}
		}
	}

	private void setWalkDirection(BetterCharacterControl characterControl,
			Vector3f vector) {
		characterControl.setWalkDirection(vector);
	}

	private void rotatePlayersNodes() {
		node2.lookAt(
				new Vector3f(node4.getWorldTranslation().x, -1000, node4
						.getWorldTranslation().z), Vector3f.UNIT_Y);
		node4.lookAt(
				new Vector3f(node2.getWorldTranslation().x, -1000, node2
						.getWorldTranslation().z), Vector3f.UNIT_Y);
	}

	private void setAnimation(AnimChannel animationChannel, String animationName) {
		if (!animationChannel.getAnimationName().equals(animationName)) {
			animationChannel.setAnim(animationName);
		}
	}

	private void setVector(Vector3f vector, Vector3f value) {
		vector.addLocal(value);
	}

	@Override
	public void onAction(String name, boolean value, float tpf) {
		initKeys(name, value, tpf);
		initCombos(name, value, tpf);
	}

	private void initKeys(String name, boolean value, float tpf) {
		switch (name) {
			case leftKeyName: {
				if (value) 	left = true;
				else 		left = false;
				break;
			}
			case rightKeyName: {
				if (value) 	right = true;
				else 		right = false;
				break;
			}
			case upKeyName: {
				if (value) 	up = true;
				else 		up = false;
				break;
			}
			case downKeyName: {
				if (value) 	down = true;
				else 		down = false;
				break;
			}
			case spaceKeyName: {
				if (value) 	space = true;
				else 		space = false;
				break;
			}
		}
	}

	private void initCombos(String name, boolean isPressed, float tpf) {
		if (isPressed) {
			pressedMappings.add(name);
		} else {
			pressedMappings.remove(name);
		}

		List<ComboMove> invokedMoves = new ArrayList<ComboMove>();
		ComboMove comboToAdding = null;
		if (upAndLeftExec.updateState(pressedMappings, time)) {
			comboToAdding = upAndLeft;
		} else if (upAndRightExec.updateState(pressedMappings, time)) {
			comboToAdding = upAndRight;
		} else if (downAndLeftExec.updateState(pressedMappings, time)) {
			comboToAdding = downAndLeft;
		} else if (downAndRightExec.updateState(pressedMappings, time)) {
			comboToAdding = downAndRight;
		} else if (leftJabComboExec.updateState(pressedMappings, time)) {
			comboToAdding = leftJabCombo;
		}
		
		if (comboToAdding != null) {
			invokedMoves.add(comboToAdding);
		}
		
		if (invokedMoves.size() > 0) {
			float priority = 0;
			ComboMove toExec = null;
			for (ComboMove move : invokedMoves) {
				if (move.getPriority() > priority) {
					priority = move.getPriority();
					toExec = move;
				}
			}
			if (currentMove != null
					&& currentMove.getPriority() > toExec.getPriority()) {
				return;
			}
			currentMove = toExec;
			currentMoveCastTime = currentMove.getCastTime();
		}
	}

	@Override
	public void onAnimChange(AnimControl arg0, AnimChannel arg1, String arg2) {
	}

	@Override
	public void onAnimCycleDone(AnimControl arg0, AnimChannel arg1, String arg2) {
	}

}
