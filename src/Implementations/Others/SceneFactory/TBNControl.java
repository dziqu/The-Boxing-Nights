package Implementations.Others.SceneFactory;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.control.VehicleControl;

public class TBNControl {
	
	public RigidBodyControl getRigidBodyControl() {
		return new RigidBodyControl();
	}
	
	public RigidBodyControl getRigidBodyControl(float f) {
		return new RigidBodyControl(f);
	}
	
	public GhostControl getGhostControl() {
		return new GhostControl();
	}
	
	public VehicleControl getVehicleControl() {
		return new VehicleControl();
	}
	
	public CharacterControl getCharacterControl() {
		return new CharacterControl();
	}
	
	public BetterCharacterControl getBetterCharacterControl() {
		return new BetterCharacterControl();
	}
	
	public BetterCharacterControl getBetterCharacterControl(float x, float y, float z) {
		return new BetterCharacterControl(x, y, z);
	}

}
