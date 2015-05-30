package Enums;

public enum StencilBits {
	OFF(0), ON(8);
    
    private final Integer numberOfStencilBits;
    
    private StencilBits(Integer numberOfStencilBits) {
        this.numberOfStencilBits = numberOfStencilBits;
    }
    
    public Integer getNumberOfStencilBits() {
        return numberOfStencilBits;
    }
}
