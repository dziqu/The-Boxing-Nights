package Implementations.Models;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResults;
import com.jme3.input.ChaseCamera;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.plugins.blender.BlenderModelLoader;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

import Implementations.Controllers.TBNPlayerComputerGameController;
import Implementations.Others.IO;
import Interfaces.PvCGameModel;
import jme3test.bullet.PhysicsTestHelper;

public class TBNPlayerComputerGameModel extends AbstractAppState implements
		PvCGameModel, AnimEventListener, ActionListener {

	private final SimpleApplication app;
	private final TBNPlayerComputerGameController tbnPlayerComputerGameController;
	private final AssetManager assetManager;
	private final Node rootNode;
	private final InputManager inputManager;
	private final Camera cam;
	private AnimControl control;
	private AnimChannel channel;
	private Node player;
	private BulletAppState bulletAppState;
	private AppStateManager stateManager;
	private Node gameLevel;
	private CharacterControl character;
	private Node model;
	private AnimControl animationControl;
	private AnimChannel animationChannel;
	private AnimChannel attackChannel;
	private FlyByCamera flyCam;
	private ChaseCamera chaseCam;
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private RenderManager renderManager;
	private ChaseCamera chaseCam2;
	private Material matRock;

	public TBNPlayerComputerGameModel(
			final SimpleApplication app,
			final TBNPlayerComputerGameController tbnPlayerComputerGameController) {
		this.app = app;
		this.tbnPlayerComputerGameController = tbnPlayerComputerGameController;
		this.assetManager = this.app.getAssetManager();
		this.rootNode = this.app.getRootNode();
		this.inputManager = this.app.getInputManager();
		this.cam = this.app.getCamera();
		stateManager = this.app.getStateManager();
		flyCam = this.app.getFlyByCamera();
		renderManager = this.app.getRenderManager();

		bulletAppState = new BulletAppState();
		stateManager.attach(bulletAppState);
	}

	@Override
	public void loadGame() {
		// player = (Node) assetManager
		// .loadModel("Assets/Models/Genesis2Male-geom.mesh.xml");
		// player.setLocalScale(0.01f);
		// player.rotate(90f, 0f, 0);
		// player.setName("player");
		// rootNode.attachChild(player);
		// control = player.getControl(AnimControl.class);
		// control.addListener(this);
		// channel = control.createChannel();
		// channel.setAnim("my_animation");

		PhysicsTestHelper.createPhysicsTestWorld(rootNode, assetManager,
				bulletAppState.getPhysicsSpace());
		
		assetManager.registerLoader(BlenderModelLoader.class, "blend");
		Spatial scene = assetManager.loadModel("Assets/Scenes/scene.blend");
		Node mesh = (Node) assetManager.loadModel("Assets/Models/mesh.blend");
		rootNode.attachChild(scene);
		rootNode.attachChild(mesh);

//		assetManager.registerLocator(
//				"http://jmonkeyengine.googlecode.com/files/quake3level.zip",
//				HttpZipLocator.class);
		
//		MaterialList matList = (MaterialList) assetManager
//				.loadAsset("Scene.material");
//		OgreMeshKey key = new OgreMeshKey("main.meshxml", matList);
		gameLevel = (Node) assetManager.loadModel("Assets/Scenes/scene.blend"); /* assetManager.loadAsset(key); */
		gameLevel.setLocalTranslation(-20, -16, 20);
		gameLevel.setLocalScale(0.10f);
		gameLevel.addControl(new RigidBodyControl(0));
		rootNode.attachChild(gameLevel);
		bulletAppState.getPhysicsSpace().addAll(gameLevel);

		AmbientLight light = new AmbientLight();
		light.setColor(ColorRGBA.White.mult(2));
		rootNode.addLight(light);

		CapsuleCollisionShape capsule = new CapsuleCollisionShape(1f, 1f);
		character = new CharacterControl(capsule, 0.05f);
		character.setJumpSpeed(20f);
		model = (Node) assetManager.loadModel("Assets/Models/Genesis2Male-geom.mesh.xml");
		
		model.setLocalScale(0.02f);
		model.addControl(character);
		bulletAppState.getPhysicsSpace().add(character);
		rootNode.attachChild(model);

		animationControl = model.getControl(AnimControl.class);
		animationControl.addListener(this);
		animationChannel = animationControl.createChannel();
		attackChannel = animationControl.createChannel();

		flyCam.setEnabled(false);
//		TODO: Zmiana ustawień kamery
		cam.setViewPort(0f, 1f, 0f, 1f);
		cam.setLocation(new Vector3f(3.32f, 4.48f, 4.28f));
		cam.setRotation(new Quaternion(-0.07f, 0.92f, -0.25f, -0.27f));
		chaseCam = new ChaseCamera(cam, model, inputManager);
		
//		TODO: Dodanie kamery
		Camera cam2 = cam.clone();
		cam2.setViewPort(.4f, .6f, 0.8f, 1f);
		cam2.setLocation(new Vector3f(-0.10f, 1.57f, 4.81f));
		cam2.setRotation(new Quaternion(0.00f, 0.99f, -0.04f, 0.02f));
		ViewPort viewPort2 = renderManager.createMainView("PiP", cam2);
		viewPort2.setClearFlags(true, true, true);
		viewPort2.attachScene(rootNode);
		chaseCam2 = new ChaseCamera(cam2, model, inputManager);

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
		if (binding.equals("CharRight"))
			character.setViewDirection(new Vector3f(90f, -90f, 0f));
		if (binding.equals("CharAttack")) {
			character.jump();
			attack();
		}
	}

	private void attack() {
		attackChannel.setAnim("my_animation", 0.1f);
		attackChannel.setLoopMode(LoopMode.DontLoop);
	}

	public void getCollisionWithCursor() {
		CollisionResults results = new CollisionResults();
		Vector2f click2d = inputManager.getCursorPosition();
		Vector3f click3d = cam.getWorldCoordinates(
				new Vector2f(click2d.x, click2d.y), 0f).clone();
		Vector3f dir = cam
				.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f)
				.subtractLocal(click3d).normalizeLocal();
		Ray ray = new Ray(click3d, dir);
		rootNode.collideWith(ray, results);
		if (results.size() > 0) {
			Geometry target = results.getClosestCollision().getGeometry();
			IO.printL(target.getName());
		}
	}

	@Override
	public void onAnimChange(AnimControl arg0, AnimChannel arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimCycleDone(AnimControl arg0, AnimChannel arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	/** Custom Keybindings: Mapping a named action to a key input. */
	private void initKeys() {
		inputManager.addMapping("Walk", new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addListener(actionListener, "Walk");
	}

	/** Definining the named action that can be triggered by key inputs. */
	private ActionListener actionListener = new ActionListener() {
		public void onAction(String name, boolean keyPressed, float tpf) {
			if (name.equals("Walk") && !keyPressed) {
				if (!channel.getAnimationName().equals("Walk")) {
					/** Play the "walk" animation! */
					channel.setAnim("Walk", 0.50f);
					channel.setLoopMode(LoopMode.Loop);
				}
			}
		}
	};

}
