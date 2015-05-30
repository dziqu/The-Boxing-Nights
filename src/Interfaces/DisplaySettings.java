package Interfaces;

import Enums.ColorDepth;
import Enums.Multisampling;
import Enums.Renderers;
import Enums.StencilBits;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;

public interface DisplaySettings {
	
	public void setSimpleApplicationInstance(SimpleApplication app);
	
	public SimpleApplication getSimpleApplicationInstance();
	
	public void setSettings(AppSettings settings);
	
	public AppSettings getSettings();
	
	public void getDefaultSettings();
	
	public void saveDefaultSettings();
	
	public void saveDefaultSettingsAsTempSettings();
	
	public void loadTempDatas();
	
	public void setDisplaySettings();
	
	public void setShowSettings(Boolean showSettings);
	
	public void setRenderer(Renderers rendererType);
	
	public String getRenderer();
	
	public void setColorDepth(ColorDepth colorDepth);
	
	public int getColorDepth();
	
	public void setFrameRate(Integer frameRate);
	
	public int getFrameRate();
	
	public void setFullscreen(Boolean fullscreenModeIsOn);
	
	public void setWidth(Integer width);
	
	public int getWidth();
	
	public void setHeight(Integer height);
	
	public int getHeight();
	
	public void setResolution(Integer width, Integer height);
	
	public void setSamples(Multisampling sample);
	
	public int getSamples();
	
	public void setVSync(Boolean vSyncIsOn);
	
	public void setFrequency(Integer frequency);
	
	public int getFrequency();
	
	public void setBitsPerPixel(StencilBits stencilBits);
	
	public int getBitPerPixel();
	
	public void save(String name);
	
	public void load(String name);
	
	public void copy(AppSettings source, AppSettings target);
	
	public void setProgramSettings();

}
