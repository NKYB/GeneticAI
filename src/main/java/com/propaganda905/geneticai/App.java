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
        
        int data_num_rows = 20;
        int data_num_cols = 2;
        int output_num_slots = 5;     
        int num_kernels = 128;
        
        int[] config = new int[10];
        config[0] = data_num_rows; // number of input lines
        config[1] = data_num_cols; // number of input columns
        config[2] = output_num_slots; // number of output slots
        config[3] = num_kernels; // number of kernel executers
        
        int[] seeds = new int[1000];
        for (int i = 0; i < 1000; i++) {
            seeds[i] = (int)((java.lang.Math.random()*89999999) + 10000000);
        }
             
        int[] output = new int[output_num_slots * num_kernels];
        
        final float[] data = new float[20*3];
        for (int i = 0; i < 20*3; i=i+3) {
            data[i] = (int)(java.lang.Math.random()*100);
            data[i+1] = (int)(java.lang.Math.random()*100);
            data[i+2] = data[i] * data[i+1];
        }
        
        Math kernel = new Math(data, output, seeds, config);
//        kernel.execute(num_kernels);
        kernel.run();

        for (int i = 0; i < num_kernels * output_num_slots; i++) {
            System.out.println( "Result[" + i + "]: " + kernel.output[i] );
        }
        
        kernel.dispose();
    }
}