package com.propaganda905.geneticai;

import com.propaganda905.geneticai.kernels.Basic;
import com.propaganda905.geneticai.kernels.Math;
import com.propaganda905.geneticai.kernels.Word;

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
        int data_num_rows = 20;
        int data_num_cols = 2;
        int output_num_slots = 5;     
        int num_kernels = 16;
        int output_stats_slots = 10;
        
        int[] config = new int[10];
        config[0] = data_num_rows;      // number of input lines
        config[1] = data_num_cols;      // number of input columns
        config[2] = output_num_slots;   // number of output slots
        config[3] = num_kernels;        // number of kernel executers
        config[4] = output_stats_slots; // number of slots for output stats
        
        int[] seeds = new int[1000];
        for (int i = 0; i < 1000; i++) {
            seeds[i] = (int)((java.lang.Math.random()*89999999) + 10000000);
        }
             
        int[] output = new int[output_num_slots * num_kernels];
        int[] output_stats = new int[output_stats_slots * num_kernels];
        
        final float[] data = new float[data_num_rows*3];
        for (int i = 0; i < data_num_rows*3; i=i+3) {
            data[i] = (int)(java.lang.Math.random()*100);
            data[i+1] = (int)(java.lang.Math.random()*100);
            data[i+2] = data[i] * data[i+1] + data[i];
        }
        
        Math kernel = new Math(data, output, output_stats, seeds, config);
//        kernel.execute(num_kernels);
        kernel.run();

        int foundAnswerFlag = 0;
        float sum = 0;
        for (int i = 0; i < num_kernels * output_stats_slots; i++) {
            
            if (kernel.output_stats[i] > 0){
                foundAnswerFlag = 1;
                if (i%output_stats_slots==0){
                    System.out.println( "Found in # iterations: " + kernel.output_stats[i] );
                } else {
                    
                    System.out.println( "  Word[" + i + "]: " + toString((int)kernel.output_stats[i], data, sum) );
                    sum = wordToNum(sum, (int)kernel.output_stats[i], data);
                }
            }
            if ((i%output_stats_slots==output_stats_slots-1) && (foundAnswerFlag == 1)){
                System.out.println( "  Sum    : " + sum);
                System.out.println( "  Target : " + data[data_num_cols]);
                foundAnswerFlag = 0;
                sum=0;
            }
        }
        
//        for (int i = 0; i < num_kernels * output_num_slots; i++) {
//            System.out.println( "Word[" + i + "]: " + kernel.output[i] );
//        }
        System.out.println("Execution mode = "+kernel.getExecutionMode());
        kernel.dispose();
    }
    
    public float wordToNum(float sum, int word, float[] data){
        int action = Word.findAction(word);
        int dataIndex = Word.findDataIndex(word);
        return Math.findSumOfAction(sum, action, data[dataIndex]);
    }
    
    public String toString(int word, float[] data, float sum){
        int action = Word.findAction(word);
        int dataIndex = Word.findDataIndex(word);
        String actionPhrase = "";
        if (action == 0){
            actionPhrase = "+";
        } else if (action == 1){
            actionPhrase = "-";
        } else if (action == 2){
            actionPhrase = "*";
        } else if (action == 3){
            actionPhrase = "/";
        }
        return sum + " " + actionPhrase + " " +  data[dataIndex] + " (" + word + ")";
    }
}