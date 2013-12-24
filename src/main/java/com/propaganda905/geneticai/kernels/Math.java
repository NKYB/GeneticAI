package com.propaganda905.geneticai.kernels;

import com.amd.aparapi.Kernel;

public class Math extends Kernel {
    //config
    private final int data_num_rows;
    private final int data_num_cols;
    private final int output_num_slots;
    private final int num_kernels;
    private final int output_stats_slots;
    private final int generation_num;
    private final int num_seeds;

    private final float[] data;
    public int[] output;
    public int[] output_stats;
    private final int[] seeds;
    
    public Math(float[] data, int[] config) {
        data_num_rows       = config[0];
        data_num_cols       = config[1];
        output_num_slots    = config[2];
        num_kernels         = config[3];
        output_stats_slots  = config[4];
        generation_num      = config[5];
        num_seeds           = config[6];
        
        this.data = data.clone();
        
        this.seeds = Config.getSeeds(num_seeds);
        this.output = Config.getOutput(output_num_slots, num_kernels);
        this.output_stats = Config.getOutput_stats(output_stats_slots, num_kernels);
    }

    public static float findSum(int output_num_slots, int gid, int[] output, float[] data, int dataRow){
        float sum = 0;
        for(int k=0; k < output_num_slots;k++){
            int word_temp = output[(gid * output_num_slots) + k];
            if (word_temp>0){
                int action = Word.findAction(word_temp);
                int dataIndex = Word.findDataIndex(word_temp);
                float dataValue = data[dataRow + dataIndex];
                sum = findSumOfAction(sum,action,dataValue);
            }
        }
        return sum;
    }

    public static float findSumOfAction(float sum, int action, float dataValue){
        if (action == 0){
            sum = sum + dataValue;
        }
        if (action == 1){
            sum = sum - dataValue;
        }
        if (action == 2 && sum != 0){
            sum = sum * dataValue;
        }
        if (action == 3 && sum != 0 && dataValue != 0){
            sum = sum / dataValue;
        }
        return sum;
    }

    public static float findDiff(float target, float sum){
        float diff = 0;
        if (target >= sum){
            diff = (target - sum);
        } else {
            diff = (sum - target);
        }
        return diff;
    }

    @Override
    public void run() {
        // preseed the random number index
        int gid = getGlobalId();
        int seedIndex=Random.setIndex(gid, 1000);
        float score = 100000;
        
        for(int i=0; i < generation_num;i++){

            // get index of word to change
            int index = Random.next(0, output_num_slots - 1, seeds, seedIndex);
            seedIndex=Random.setIndex(++seedIndex,  1000);

            // should I change or delete
            int change_method = Random.next(0, 1, seeds, seedIndex) * 1;
            seedIndex=Random.setIndex(++seedIndex,  1000);
            int hold_word = output[(gid * output_num_slots) + index];
            if (change_method < 1){ // change to new word
                // create word since new word was selected as change method
                int word = Word.createWord(seeds,seedIndex,data_num_cols-1);
                seedIndex=seedIndex+2;
                seedIndex=Random.setIndex(seedIndex,  1000);
                output[(gid * output_num_slots) + index] = word;
            } else { // delete the current word
                output[(gid * output_num_slots) + index] = 0;
            }
            
            //eval
            float sub_score = 0;
            for(int j=0; j < data_num_rows;j=j+data_num_cols+1){
                float sum = findSum(output_num_slots, gid, output, data, j);
                float target = data[j + data_num_cols];
                sub_score = sub_score + Math.findDiff(target, sum);
            }
            if (sub_score < score){
                score = sub_score;
            } else {
                output[(gid * output_num_slots) + index] = hold_word;
            }
            if (score == 0){
                output_stats[(gid * output_stats_slots)] = i;
                i=generation_num;
            }
        }
        if (output_stats[(gid * output_stats_slots)]==0){
            output_stats[(gid * output_stats_slots)] = generation_num;
        }
        for(int k=0; k < output_num_slots;k++){
//            System.out.println("word = " + output[(gid * output_num_slots) + k]);
            int word_temp = output[(gid * output_num_slots) + k];
            if (word_temp>0){
                output_stats[(gid * output_stats_slots) + k + 1] = word_temp;
            }
        }
    }
}
