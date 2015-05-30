package Enums;

public enum Renderers {
	
	OpenGL1("LWJGL-OPENGL1"), OpenGL2("LWJGL-OpenGL2"), OpenGL3("LWJGL-OpenGL3");
    
    private final String renderer;
    
    private Renderers(String renderer) {
        this.renderer = renderer;
    }
    
    public String getRenderer() {
        return renderer;
    }

}
