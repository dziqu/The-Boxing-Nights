package Implementations.Others.SceneFactory;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;

public class ScenesFactory {
	
	private SimpleApplication app = null;
	private ScenesFactory scenesFactory = null;
	private Scene scene = null;

	public ScenesFactory(Application app) {
		this.app = (SimpleApplication) app;
		createScene();
		createPlayersFactory();
	}
	
	public void createScene() {
		scene = new Scene(app);
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public void createPlayersFactory() {
		scene.createSceneObjectsFactory();
	}
	
	public TBNSceneObjectsFactory getPlayersFactory() {
		return scene.getSceneObjectsFactory();
	}

}
