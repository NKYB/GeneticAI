package com.propaganda905.geneticai.kernels;

import com.amd.aparapi.Kernel;

/**
 * Kernel with math functions
 * 
 * @author Administrator
 */
public class Math extends Kernel {
    /**
     * 
     * @param result
     * @param seeds 
     */
    public Math(float result[], int[] seeds) {
        this.result = result.clone();
        this.seeds = seeds.clone();
    }
    
    /**
     * Result of kernel
     */
    private float[] result;
    
    /**
     * Input data to analyze
     */
//    private final float[][] inputData;
    
    /**
     * list of random numbers
     */
    private int[] seeds;

    /**
     * 
     * @param index
     * 
     * @return 
     */
    public float getResult(int index) {
        return result[index];
    }

    /**
     * Basic kernel using aparapi
     */
    @Override
    public void run() {
        int gid = getGlobalId();
        int seedIndex=Random.setIndex(gid, 1000);
        
        int rndIndex = Random.next(0, 10, seeds, seedIndex);
//        seedIndex=Random.setIndex(++seedIndex,  1000);
        
        result[gid] = rndIndex;
    }
   
}
