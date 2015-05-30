package Implementations.Others;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.prefs.BackingStoreException;

import Enums.ColorDepth;
import Enums.Multisampling;
import Enums.Names;
import Enums.Renderers;
import Enums.StencilBits;
import Interfaces.DisplaySettings;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;

public class TBNDisplaySettings implements DisplaySettings {
	
	private SimpleApplication app;
	private AppSettings settings;
	
	public TBNDisplaySettings(SimpleApplication app) {
		setSimpleApplicationInstance(app);
		setSettings(new AppSettings(true)); }
	
	@Override
	public void setSimpleApplicationInstance(SimpleApplication app) { this.app = app; }
	
	@Override
	public SimpleApplication getSimpleApplicationInstance() { return app; }
	
	@Override
	public void setSettings(AppSettings settings) { this.settings = settings; }
	
	@Override
	public AppSettings getSettings() { return settings; }
	
	@Override
	public void getDefaultSettings() {
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		DisplayMode[] displayModesTable = device.getDisplayModes();
		int sizeOfDisplayModesTable = displayModesTable.length;
		if (sizeOfDisplayModesTable > 0) {
			setRenderer(Renderers.OpenGL2);
			setColorDepth(ColorDepth.BPP32);
			setFrameRate(60);
			setFullscreen(true);
			int height = 768;
			int width = 1024;
			setResolution(width, height);
			setSamples(Multisampling.FOUR_SAMPLES);
			setVSync(true);
		}
	}
	
	@Override
	public void saveDefaultSettings() {
		save(Names.DEFAULT_SETTINGS_DIRECTORY.getLocation());
	}
	
	@Override
	public void saveDefaultSettingsAsTempSettings() {
		save(Names.TEMPORARY_SETTINGS_DIRECTORY.getLocation());
	}
	
	@Override
	public void loadTempDatas() {
		load(Names.TEMPORARY_SETTINGS_DIRECTORY.getLocation());
	}
	
	@Override
	public void setDisplaySettings() {
		setProgramSettings();
	}
	
	@Override
	public void setShowSettings(Boolean showSettings) {
		getSimpleApplicationInstance().setShowSettings(showSettings);
	}
	
	@Override
	public void setRenderer(Renderers rendererType) { 
		getSettings().setRenderer(rendererType.getRenderer()); }
	
	@Override
	public String getRenderer() { return getSettings().getRenderer(); }
	
	@Override
	public void setColorDepth(ColorDepth colorDepth) { 
		getSettings().setBitsPerPixel(colorDepth.getNumberOfBitsPerPixels()); }
	
	@Override
	public int getColorDepth() { return getSettings().getBitsPerPixel(); }
	
	@Override
	public void setFrameRate(Integer frameRate) {
		getSettings().setFrameRate(frameRate); }
	
	@Override
	public int getFrameRate() { return getSettings().getFrameRate(); }
	
	@Override
	public void setFullscreen(Boolean fullscreenModeIsOn) {
		getSettings().setFullscreen(fullscreenModeIsOn); }
	
	@Override
	public void setWidth(Integer width) {
		getSettings().setWidth(width); }
	
	@Override
	public int getWidth() { return getSettings().getWidth(); }
	
	@Override
	public void setHeight(Integer height) {	
		getSettings().setHeight(height); }
	
	@Override
	public int getHeight() { return getSettings().getHeight(); }
	
	@Override
	public void setResolution(Integer width, Integer height) {
		getSettings().setResolution(width, height); }
	
	@Override
	public void setSamples(Multisampling sample) {
		getSettings().setSamples(sample.getNumberOfSamples()); }
	
	@Override
	public int getSamples() { return getSettings().getSamples(); }
	
	@Override
	public void setVSync(Boolean vSyncIsOn) {
		getSettings().setVSync(vSyncIsOn); }
	
	@Override
	public void setFrequency(Integer frequency) { 
		getSettings().setFrequency(frequency); }
	
	@Override
	public int getFrequency() { return getSettings().getFrequency(); }
	
	@Override
	public void setBitsPerPixel(StencilBits stencilBits) {
		getSettings().setBitsPerPixel(stencilBits.getNumberOfStencilBits()); }
	
	@Override
	public int getBitPerPixel() { return getSettings().getBitsPerPixel(); }

	@Override
	public void save(String name) { 
		try { getSettings().save(name); } 
		catch (BackingStoreException e) { e.printStackTrace(); } }

	@Override
	public void load(String name) { 
		try { getSettings().load(name); } 
		catch (BackingStoreException e) { e.printStackTrace(); } }
	
	@Override
	public void copy(AppSettings source, AppSettings target) {
		try {target.copyFrom(source);}
		catch (Exception ex) {ex.printStackTrace();}}
	
	@Override
	public void setProgramSettings() {
		getSimpleApplicationInstance().setSettings(getSettings()); }

}
