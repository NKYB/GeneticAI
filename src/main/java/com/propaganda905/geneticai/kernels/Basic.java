package com.propaganda905.geneticai.kernels;

import com.amd.aparapi.Kernel;

public class Basic extends Kernel {
        
    public float[] result;

    public void setup(){
        result = new float[20];
    }

    @Override
    public void run() {
        int i= getGlobalId();
        result[i]=5;
    }
}
