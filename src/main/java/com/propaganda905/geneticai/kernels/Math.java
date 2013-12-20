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
    public Math(float[][] inputData, float result[], int[] seeds) {
        this.inputData = inputData.clone();
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
    private float[][] inputData;
    
    /**
     * list of random numbers
     */
    private int[] seeds;

    /**
     * 
     * @param index
     * @return 
     */
    public float getResult(int index) {
        return result[index];
    }
    
//    public float getSum(float value, int sign, float sum){
//        if (sign == 0){
//            sum = sum + value;
//        } else if (sign == 1){
//            sum = sum - value;
//        } else if (sign == 2){
//            if (sum != 0){
//                sum = sum * value;
//            }
//        } else if (sign == 3){
//            if (sum != 0){
//                sum = sum / value;
//            }
//        }
//        return sum;
//    }

    /**
     * Basic kernel using aparapi
     */
    @Override
    public void run() {
        int gid = getGlobalId();
        int seedIndex=Random.setIndex(gid, 1000);
        
//        for(int i=0; i < 10000;i++){
//            
//            //evolve
//            int index = Random.next(0, 20, seeds, seedIndex);
//            seedIndex=Random.setIndex(++seedIndex,  1000);
//            if (index%2==1){
//                result[index] = Random.next(0, 20, seeds, seedIndex);
//            } else {
//                
//            }
//        }
        
        
        
//        int program[] = new int[20];
        
//        float score = 0;
//        for (int m = 0; m < 20; m++) {
//            float value = inputData[m]
//            float sum = 0;
//        }
        
        int rndIndex = Random.next(0, 10, seeds, seedIndex);
//        seedIndex=Random.setIndex(++seedIndex,  1000);
        
        result[gid] = rndIndex;
    }
   
}
