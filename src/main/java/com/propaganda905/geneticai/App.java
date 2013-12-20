package com.propaganda905.geneticai;

import com.propaganda905.geneticai.kernels.Basic;
import com.propaganda905.geneticai.kernels.Math;

/**
 * Main entry point
 * 
 * @author NKYB
 */
public class App 
{
    /**
     *
     * @param args
     */
    public static void main( String[] args )
    {
        App app = new App();
        app.runMathKernel();
    }
    
    /**
     * runs the basic kernel
     */
    public void runBasicKernel(){
        Basic kernel = new Basic();
        kernel.setup();
        kernel.execute(20);

        System.out.println( "Result[0]: " + kernel.result[0] );
        kernel.dispose();
    }
    
     /**
     * runs the math kernel
     */
    public void runMathKernel(){

        int[] seeds = new int[1000];
        for (int i = 0; i < 1000; i++) {
            seeds[i] = (int)((java.lang.Math.random()*89999999) + 10000000);
        }
             
        float[] result = new float[20];
        
        final float[][] inputData = new float[20][3];
        for (int i = 0; i < 20; i++) {
            inputData[i][0] = (int)(java.lang.Math.random()*100);
            inputData[i][1] = (int)(java.lang.Math.random()*100);
            inputData[i][2] = inputData[i][0] * inputData[i][1];
        }
        
        Math kernel = new Math(inputData, result, seeds);
        kernel.execute(20);

        for (int i = 0; i < 20; i++) {
            System.out.println( "Result[" + i + "]: " + kernel.getResult(i) );
        }
        
        kernel.dispose();
    }
}