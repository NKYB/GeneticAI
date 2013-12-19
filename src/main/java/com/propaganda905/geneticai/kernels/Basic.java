package com.propaganda905.geneticai.kernels;

import com.amd.aparapi.Kernel;

public class Basic extends Kernel {
        
    public float[] result;
    
/**
 * Setup the basic kernel variables
 *
 * @return      void
 */
    public void setup(){
        result = new float[20];
    }
    
/**
 * Basic kernel using aparapi
 *
 * @return      void
 */
    @Override
    public void run() {
        int i= getGlobalId();
        result[i]=5;
    }
}
