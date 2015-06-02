package Implementations.Others.SceneFactory;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.input.ChaseCamera;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl.ControlDirection;
import com.jme3.scene.control.Control;
import com.jme3.scene.plugins.blender.BlenderModelLoader;

public class TBNSceneObject {
	
	private static int playersCounter = 0;
	private String sourceOfMesh = "Assets/Models/player/player.blend";
	private Node playerNode = null;
	private SimpleApplication app = null;
	private AssetManager assetManager;
	private String name = "";
	private Node rootNode;
	private Control control;
	private InputManager inputManager;
	private Camera cam;
	private ChaseCamera chaseCam;
	private FlyByCamera flyCam;
	
	public TBNSceneObject(Application app) {
		this.app = (SimpleApplication) app;
		assetManager = this.app.getAssetManager();
		rootNode = this.app.getRootNode();
		inputManager = this.app.getInputManager();
		cam = this.app.getCamera();
		flyCam = this.app.getFlyByCamera();
		
		assetManager.registerLoader(BlenderModelLoader.class, "blend");
	}
	
	public String getSourceOfMesh() {
		return sourceOfMesh;
	}
	
	public void setSourceOfMesh(String sourceOfMesh) {
		this.sourceOfMesh = sourceOfMesh;
	}
	
	public void setNode(String source) {
		playerNode = (Node) assetManager.loadModel(source);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Node getPlayerNode() {
		return playerNode;
	}
	
	public void addToRootNode() {
		rootNode.attachChild(playerNode);
	}
	
	public void setControl(Control control) {
		this.control = control;
	}
	
	public Control getControl() {
		return control;
	}
	
	public void addControl(Control control) {
		playerNode.addControl(control);
	}
	
	public void scale(float localScale) {
		playerNode.setLocalScale(localScale);
	}
	
	public void addCamera() {
		Camera myCam = cam.clone();
		flyCam.setEnabled(true);
		chaseCam = new ChaseCamera(cam, playerNode, inputManager);
	}

}
