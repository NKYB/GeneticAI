package com.propaganda905.geneticai.kernels;

import com.amd.aparapi.Kernel;

/**
 * Demonstrates a basic kernel
 *
 * @author NKYB
 */
public class Basic extends Kernel {
    /**
     * Result of kernel
     */
    public float[] result;

    /**
     * Setup the basic kernel variables
     */
    public void setup() {
        result = new float[20];
    }

    /**
     * Basic kernel using aparapi
     */
    @Override
    public void run() {
        int i = getGlobalId();
        result[i] = 5;
    }
}
