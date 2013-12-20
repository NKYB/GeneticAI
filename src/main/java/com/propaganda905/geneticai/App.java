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
        
        Math kernel = new Math(result,seeds);
        kernel.execute(20);

        for (int i = 0; i < 20; i++) {
            System.out.println( "Result[" + i + "]: " + kernel.getResult(i) );
        }
        
        kernel.dispose();
    }
}