package Implementations.Others.SceneFactory;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

public class Scene {
	
	private final SimpleApplication app;
	private TBNSceneObjectsFactory tbnPlayersFactory = null;
	private String sourceOfScene = "Assets/Models/ring/ring.blend";
	private AssetManager assetManager;
	private Camera cam;

	public Scene(Application app) {
		this.app = (SimpleApplication) app;
		assetManager = this.app.getAssetManager();
		cam = this.app.getCamera();
		createSceneObjectsFactory();
		createSceneObject();
	}
	
	public void createSceneObjectsFactory() {
		tbnPlayersFactory = new TBNSceneObjectsFactory(app);
	}
	
	public TBNSceneObjectsFactory getSceneObjectsFactory() {
		return tbnPlayersFactory;
	}
	
	public void createSceneObject() {
		tbnPlayersFactory.createSceneObject();
	}
	
	public TBNSceneObject getSceneObject(int numberOfPlayer) {
		return tbnPlayersFactory.getSceneObject(numberOfPlayer);
	}
	
	public void setCamLocation(float x, float y, float z) {
		cam.setLocation(new Vector3f(x, y, z));
	}

}
