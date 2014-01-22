package com.genetic.ai.kernels;

/**
 *
 * @author Administrator
 */
public class Random {
    /**
     * 
     * @param index
     * @param maxLength
     * @return 
     */
    static int setIndex(int index, int maxLength){
        if (index >= maxLength){
            index = 0;
        }
        return index;
    }
    
    /**
     * 
     * @param min
     * @param max
     * @param seeds
     * @param seedIndex
     * @return 
     */
    static int next(int min, int max, int[] seeds, int seedIndex){
       return min + (seeds[seedIndex] % (1 + max - min));
    }
    
    /**
     * 
     * @return 
     */
    int nothing(){
        return 0;
    }
}
