package com.propaganda905.geneticai;

import com.amd.aparapi.ProfileInfo;
import com.propaganda905.geneticai.kernels.Basic;
import com.propaganda905.geneticai.kernels.Config;
import com.propaganda905.geneticai.kernels.Data;
import com.propaganda905.geneticai.kernels.Math;
import com.propaganda905.geneticai.kernels.Word;
import java.util.List;

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
        
        Data data = new Data();
        data.createRandomDataAsMap(10, 4);
        
        Config config = new Config();
        config.setData_num_cols(data.getNumDataCols());
        config.setData_num_rows(data.getNumRows());
        config.setGeneration_num(1000);
        config.setNum_kernels(1440);
        config.setOutput_num_slots(5);
        config.setOutput_stats_slots(10);
        config.setNum_seeds(10000);

        app.runMathKernel(config, data);
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
    public void runMathKernel(Config config, Data data){
        Math kernel = new Math(data.getData(), config.getConfig());
        System.out.println("Execution mode = " + kernel.getExecutionMode());
//        kernel.execute(config.getNum_kernels());
        kernel.run();
        outputResult(config, data, kernel.output_stats);
        kernel.dispose();
    }
    
    public void outputResult(Config config, Data data, int[] output_stats){
        int foundAnswerFlag = 0;
        int countKernels = 0;
        float sum = 0;
        for (int i = 0; i < config.getNum_kernels() * config.getOutput_stats_slots(); i++) {
            
            if (output_stats[i] > 0){
                foundAnswerFlag = 1;
                if (i%config.getOutput_stats_slots()==0){
                    countKernels++;
                    System.out.println(countKernels + ": Found in # iterations: " + output_stats[i] );
                } else {
                    
                    System.out.println( "  Word[" + i + "]: " + toString((int)output_stats[i], data.getData(), sum) );
                    sum = wordToNum(sum, (int)output_stats[i], data.getData());
                }
            }
            if ((i%config.getOutput_stats_slots()==config.getOutput_stats_slots()-1) && (foundAnswerFlag == 1)){
                System.out.println( "  Sum    : " + sum);
                System.out.println( "  Target : " + data.getDataAtPoint(0, config.getData_num_cols()));
                foundAnswerFlag = 0;
                sum=0;
            }
        }
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
