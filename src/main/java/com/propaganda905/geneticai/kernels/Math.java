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
    public Math(float[] a, float[] b, float[] r, int result[], int[] seeds) {
        this.a = a.clone();
        this.b = b.clone();
        this.r = r.clone();
        this.result = result.clone();
        this.seeds = seeds.clone();
    }
    
    /**
     * Result of kernel
     */
    private int[] result;
    
    /**
     * Input data to analyze
     */
    private float[] a;
    private float[] b;
    private float[] r;
    
    /**
     * list of random numbers
     */
    private int[] seeds;

    /**
     * 
     * @param index
     * @return 
     */
    public int getResult(int index) {
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
        // preseed the random number index
        int gid = getGlobalId();
        int seedIndex=Random.setIndex(gid, 1000);
        
//        for(int i=0; i < 1000000;i++){
            
            //evolve
            int index = Random.next(0, 20, seeds, seedIndex);
            seedIndex=Random.setIndex(++seedIndex,  1000);
            
            //word
            int word = 100;
            word = word + Random.next(0, 3, seeds, seedIndex) * 10;
            seedIndex=Random.setIndex(++seedIndex,  1000);
            word = word + Random.next(0, 1, seeds, seedIndex) * 1;
            seedIndex=Random.setIndex(++seedIndex,  1000);
            
//            result[index] = word;
            
            //eval
//            float score = 0;
//            for(int j=0; j < 20;j++){
//                
//                float sum = 0;
//                float[] input = inputData[j];
//                float target = input[2];
//                
//                for(int k=0; k < 20;k++){
//                    int resultWord = result[k];
//                    int action = resultWord - 100 / 10;
//                    int inputIndex = resultWord - 100 - (action * 10);
//                }
//            }
//        }
        
        
        
//        int program[] = new int[20];
        
//        float score = 0;
//        for (int m = 0; m < 20; m++) {
//            float value = inputData[m]
//            float sum = 0;
//        }
        
//        int rndIndex = Random.next(0, 10, seeds, seedIndex);
//        seedIndex=Random.setIndex(++seedIndex,  1000);
        
        result[gid] = word;
    }
   
}
