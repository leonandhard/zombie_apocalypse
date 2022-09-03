package com.example.zombie_apocalypse.exception;

public class InputDimensionsUnexpectedException extends RuntimeException {
    public InputDimensionsUnexpectedException(Integer wrongDimensions) {
        super("Expect the dimensions >= 1,but get "+ wrongDimensions);
    }
}
