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
//        int test = 100;
//        int a = ((test - (test % 100)) / 100);
//        int b = (test - (a*100)) / 10;
//        int c = (test % 10);
//        System.out.println( "Test a: " +  a);
//        System.out.println( "Test b: " +  b);
//        System.out.println( "Test c: " +  c);
        
        int[] seeds = new int[1000];
        for (int i = 0; i < 1000; i++) {
            seeds[i] = (int)((java.lang.Math.random()*89999999) + 10000000);
        }
             
        int[] result = new int[20];
        
        final float[] a = new float[20];
        final float[] b = new float[20];
        final float[] r = new float[20];
        for (int i = 0; i < 20; i++) {
            a[i] = (int)(java.lang.Math.random()*100);
            b[i] = (int)(java.lang.Math.random()*100);
            r[i] = a[i] * b[i];
        }
        
        Math kernel = new Math(a,b,r, result, seeds);
        kernel.execute(20);

        for (int i = 0; i < 20; i++) {
            System.out.println( "Result[" + i + "]: " + kernel.getResult(i) );
        }
        
        kernel.dispose();
    }
}