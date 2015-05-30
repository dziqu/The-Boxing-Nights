package Enums;

public enum Multisampling {
	
	ZERO_SAMPLES(0), ONE_SAMPLE(1), TWO_SAMPLES(2), FOUR_SAMPLES(4), EIGHT_SAMPLES(8), SIXTEEN_SAMPLES(16), THIRTY_TWO_SAMPLES(32);
    
    private final Integer numberOfSamples;
    
    private Multisampling(Integer numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }
    
    public Integer getNumberOfSamples() {
        return numberOfSamples;
    }

}
