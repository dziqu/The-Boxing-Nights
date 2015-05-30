package Implementations.Others;

import java.util.HashMap;
import java.util.Map;

import com.jme3.input.KeyInput;

public class PlayersControl {
	
	private Map <String, Integer> map;
	
	public PlayersControl() {
		initMap();
	}
	
	public void initMap() {
		setMap(new HashMap<String, Integer>());
		addToMap("0",  KeyInput.KEY_0);
		addToMap("1",  KeyInput.KEY_1);
		addToMap("2",  KeyInput.KEY_2);
		addToMap("3",  KeyInput.KEY_3);
		addToMap("4",  KeyInput.KEY_4);
		addToMap("5",  KeyInput.KEY_5);
		addToMap("6",  KeyInput.KEY_6);
		addToMap("7",  KeyInput.KEY_7);
		addToMap("8",  KeyInput.KEY_8);
		addToMap("9",  KeyInput.KEY_9);
		addToMap("A",  KeyInput.KEY_A);
		addToMap("B",  KeyInput.KEY_B);
		addToMap("C",  KeyInput.KEY_C);
		addToMap("D",  KeyInput.KEY_D);
		addToMap("E",  KeyInput.KEY_E);
		addToMap("F",  KeyInput.KEY_F);
		addToMap("G",  KeyInput.KEY_G);
		addToMap("H",  KeyInput.KEY_H);
		addToMap("I",  KeyInput.KEY_I);
		addToMap("J",  KeyInput.KEY_J);
		addToMap("K",  KeyInput.KEY_K);
		addToMap("L",  KeyInput.KEY_L);
		addToMap("M",  KeyInput.KEY_M);
		addToMap("N",  KeyInput.KEY_N);
		addToMap("O",  KeyInput.KEY_O);
		addToMap("P",  KeyInput.KEY_P);
		addToMap("Q",  KeyInput.KEY_Q);
		addToMap("R",  KeyInput.KEY_R);
		addToMap("S",  KeyInput.KEY_S);
		addToMap("T",  KeyInput.KEY_T);
		addToMap("U",  KeyInput.KEY_U);
		addToMap("V",  KeyInput.KEY_V);
		addToMap("W",  KeyInput.KEY_W);
		addToMap("X",  KeyInput.KEY_X);
		addToMap("Y",  KeyInput.KEY_Y);
		addToMap("Z",  KeyInput.KEY_Z);
		addToMap("NUMPAD0",  KeyInput.KEY_NUMPAD0);
		addToMap("NUMPAD1",  KeyInput.KEY_NUMPAD1);
		addToMap("NUMPAD2",  KeyInput.KEY_NUMPAD2);
		addToMap("NUMPAD3",  KeyInput.KEY_NUMPAD3);
		addToMap("NUMPAD4",  KeyInput.KEY_NUMPAD4);
		addToMap("NUMPAD5",  KeyInput.KEY_NUMPAD5);
		addToMap("NUMPAD6",  KeyInput.KEY_NUMPAD6);
		addToMap("NUMPAD7",  KeyInput.KEY_NUMPAD7);
		addToMap("NUMPAD8",  KeyInput.KEY_NUMPAD8);
		addToMap("NUMPAD9",  KeyInput.KEY_NUMPAD9);
	}
	
	public void setMap(Map<String, Integer> mapaZnaków) { 
		this.map = mapaZnaków; 
	}
	
	public Map<String, Integer> getMap() {return map;}
	
	public void addToMap(String wartość1, Integer wartość2) {
		getMap().put(wartość1, wartość2);
	}
	
	public Integer getValue(String value) {
		return getMap().get(value);
	}

}
