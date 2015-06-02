package Implementations.Others.SceneFactory;

import java.util.ArrayList;
import java.util.List;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;

public class TBNSceneObjectsFactory {
	
	private SimpleApplication app = null;
	private List <TBNSceneObject> listOfSceneObjects = null;

	public TBNSceneObjectsFactory(Application app) {
		this.app = (SimpleApplication) app;
		listOfSceneObjects = new ArrayList();
	}
	
	public void createSceneObject() {
		listOfSceneObjects.add(new TBNSceneObject(app));
	}
	
	public TBNSceneObject getSceneObject(int numberOfPlayer) {
		TBNSceneObject player = null;
		if (numberOfPlayer > listOfSceneObjects.size()) {
			throw new IllegalArgumentException();
		} else {
			player = listOfSceneObjects.get(numberOfPlayer);
		}
		
		return player;
	}
}
