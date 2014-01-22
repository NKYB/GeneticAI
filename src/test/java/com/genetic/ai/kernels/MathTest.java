/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genetic.ai.kernels;

import com.genetic.ai.kernels.Data;
import com.genetic.ai.kernels.Random;
import com.genetic.ai.kernels.Math;
import com.genetic.ai.kernels.Config;
import junit.framework.TestCase;

/**
 *
 * @author Administrator
 */
public class MathTest extends TestCase {
    
    public MathTest(String testName) {
        super(testName);
    }
    /**
     * Test of findfindSum method, of class Math.
     */
    public void testfindSum() {
        float[] d = new float[3];
        int[] o;
        
        d[0] = 1;
        d[1] = 2;
        d[2] = 3;
        
        o = new int[5]; o[0]=100; o[1]=101; o[2]=0; o[3]=0; o[4]=0;
        assertEquals((float)3.0, Math.findSum(5, 0, o, d, 0));

        o = new int[5]; o[0]=101; o[1]=100; o[2]=0; o[3]=0; o[4]=0;
        assertEquals((float)3.0, Math.findSum(5, 0, o, d, 0));
        
        o = new int[5]; o[0]=101; o[1]=0; o[2]=0; o[3]=0; o[4]=100;
        assertEquals((float)3.0, Math.findSum(5, 0, o, d, 0));
        
        o = new int[5]; o[0]=101; o[1]=0; o[2]=0; o[3]=100; o[4]=0;
        assertEquals((float)3.0, Math.findSum(5, 0, o, d, 0));
        
        o = new int[5]; o[0]=0; o[1]=0; o[2]=0; o[3]=101; o[4]=100;
        assertEquals((float)3.0, Math.findSum(5, 0, o, d, 0));
        
        o = new int[5]; o[0]=111; o[1]=0; o[2]=0; o[3]=0; o[4]=0;
        assertEquals((float)-2.0, Math.findSum(5, 0, o, d, 0));
        
        o = new int[5]; o[0]=111; o[1]=110; o[2]=0; o[3]=0; o[4]=0;
        assertEquals((float)-3.0, Math.findSum(5, 0, o, d, 0));
        
        o = new int[5]; o[0]=0; o[1]=130; o[2]=121; o[3]=100; o[4]=110;
        assertEquals((float)0.0, Math.findSum(5, 0, o, d, 0));
        
        o = new int[5]; o[0]=100; o[1]=101; o[2]=101; o[3]=111; o[4]=120;
        assertEquals((float)3.0, Math.findSum(5, 0, o, d, 0));
        
        d = new float[3]; d[0] = 22; d[1] = 29; d[2] = 638;
        o = new int[5]; o[0]=100; o[1]=100; o[2]=121; o[3]=0; o[4]=0;
        assertEquals((float)1276.0, Math.findSum(5, 0, o, d, 0));
        
        d = new float[3]; d[0] = 30; d[1] = 2; d[2] = 60;
        o = new int[5]; o[0]=110; o[1]=100; o[2]=121; o[3]=0; o[4]=0;
        assertEquals((float)0.0, Math.findSum(5, 0, o, d, 0));
    }
    
    /**
     * Test of findfindSumOfAction method, of class Math.
     */
    public void testfindSumOfAction() {
        assertEquals((float)20.0, Math.findSumOfAction(10, 0, 10));
        assertEquals((float)0.0, Math.findSumOfAction(10, 1, 10));
        assertEquals((float)100.0, Math.findSumOfAction(10, 2, 10));
        assertEquals((float)1.0, Math.findSumOfAction(10, 3, 10));
        assertEquals((float)10.0, Math.findSumOfAction(0, 0, 10));
        assertEquals((float)-10.0, Math.findSumOfAction(0, 1, 10));
        assertEquals((float)0.0, Math.findSumOfAction(0, 2, 10));
        assertEquals((float)0.0, Math.findSumOfAction(0, 3, 10));
        assertEquals((float)10.0, Math.findSumOfAction(10, 0, 0));
        assertEquals((float)10.0, Math.findSumOfAction(10, 1, 0));
        assertEquals((float)0.0, Math.findSumOfAction(10, 2, 0));
        assertEquals((float)10.0, Math.findSumOfAction(10, 3, 0));
    }
    
    /**
     * Test of findDiff method, of class Math.
     */
    public void testFindDiff() {
        assertEquals((float)90.0, Math.findDiff(100,10));
        assertEquals((float)110.0, Math.findDiff(100,-10));
        assertEquals((float)110.0, Math.findDiff(-100,10));
        assertEquals((float)90.0, Math.findDiff(-100,-10));
        assertEquals((float)10.0, Math.findDiff(0,10));
        assertEquals((float)10.0, Math.findDiff(10,0));
        assertEquals((float)0.0, Math.findDiff(0,0));
    }

    /**
     * Test of run method, of class Math.
     */
    public void testRun() {
        assertTrue( true );
    }
    
    public void testFindChangeMethod(){
        int[] seeds = Config.getSeeds(1000);
        for (int i = 0; i < 100; i++) {
            int change_method = Math.findChangeMethod(seeds, i);
            if (change_method != 0 && change_method != 1){
                fail("Change Method Error - Range");
            }
        }
    }
    
    public void testFindOutputIndexToModify(){
        int output_num_slots = 5;
        int[] seeds = Config.getSeeds(1000);
        for (int i = 0; i < 100; i++) {
            int output_index_to_modify = Math.findOutputIndexToModify(output_num_slots,seeds, i);
            if (output_index_to_modify < 0 && output_index_to_modify >= output_num_slots){
                fail("Change Method Error - output_index_to_modify: " + output_index_to_modify);
            }
        }
    }
    
    public void testFindNewOutputWord(){
        // findNewOutputWord(int output_index_to_modify, int data_num_cols, int[] output, int[] seeds, int seedIndex)
        float[] d = new float[6];
        d[0] = 1;
        d[1] = 2;
        d[2] = 3;
        d[3] = 4;
        d[4] = 5;
        d[5] = 9;
        
        Data data = new Data();
        data.setNumCols(2);
        data.setData(d);
        
        Config config = new Config();
        config.setData_num_cols(data.getNumDataCols());
        config.setData_num_rows(data.getNumRows());
        config.setGeneration_num(1000);
        config.setNum_kernels(1440);
        config.setOutput_num_slots(10);
        config.setOutput_stats_slots(10);
        config.setNum_seeds(1000);
        
        int[] seeds = Config.getSeeds(1000);
        
        int[] output = Config.getOutput(config.getOutput_num_slots(),config.getNum_kernels());
        
        int data_num_cols = config.getData_num_cols();
        int seedIndex = 0;
        
        for(int i=0; i < config.getGeneration_num();i++){
            for(int j=0; j < 2;j++){
                int gid = j;
                int random_index_to_modify = Math.findOutputIndexToModify(config.getOutput_num_slots(), seeds, seedIndex);
                int output_index_to_modify = (gid * config.getOutput_num_slots()) + random_index_to_modify;
                seedIndex=Random.setIndex(++seedIndex,  1000);

                int new_word = Math.findNewOutputWord(output_index_to_modify, data_num_cols, output, seeds, seedIndex);
                output[output_index_to_modify] = new_word;
                System.out.println(i + " - gid:" + gid + " index_to_modify: " + output_index_to_modify + " word: " + output[output_index_to_modify]);
            
                if (
                        new_word != 0 &&
                        new_word != 100 &&
                        new_word != 101 &&
                        new_word != 110 &&
                        new_word != 111 &&
                        new_word != 120 &&
                        new_word != 121 &&
                        new_word != 130 &&
                        new_word != 131
                ){
                    fail("FindNewOutputWord Problem");
                }
                
                if (output[output_index_to_modify] != new_word){
                    fail("Could not set output[output_index_to_modify]");
                }
            }
        }
    }
}
