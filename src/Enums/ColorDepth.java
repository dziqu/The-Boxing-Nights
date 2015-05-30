package Enums;

public enum ColorDepth {
	
	BPP1(1), BPP2(2), BPP4(4), BPP8(8), BPP24(24), BPP32(32);
    
    private final Integer numberOfBitsPerPixels;
    
    private ColorDepth(Integer numberOfBitsPerPixels) {
        this.numberOfBitsPerPixels = numberOfBitsPerPixels;
    }
    
    public Integer getNumberOfBitsPerPixels() {
        return numberOfBitsPerPixels;
    }

}
