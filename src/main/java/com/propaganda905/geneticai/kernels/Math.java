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
    
    public static int findChangeMethod(int[] seeds, int seedIndex){
        int change_method = Random.next(0, 1, seeds, seedIndex) * 1;
        return change_method;
    }
    
    public static int findOutputIndexToModify(int output_num_slots, int[] seeds, int seedIndex){
        int index = Random.next(0, output_num_slots - 1, seeds, seedIndex);
        return index;
    }
    
    public static int findNewOutputWord(int output_index_to_modify, int data_num_cols, int[] output, int[] seeds, int seedIndex){
        int word = 0;
        
        // should we create a new word or delete the current word
        int change_method = findChangeMethod(seeds, seedIndex);
        
        seedIndex=Random.setIndex(++seedIndex,  1000);
        if (change_method < 1){ 
            word = Word.createWord(seeds,seedIndex,data_num_cols-1);
        }
        return word;
    }

    @Override
    public void run() {
        // preseed the random number index
        int gid = getGlobalId();
        int seedIndex=Random.setIndex(gid, 1000);
        float score = 100000;
        
        for(int i=0; i < generation_num;i++){

            // get output index of word to change
            int random_index_to_modify = findOutputIndexToModify(output_num_slots, seeds, seedIndex);
            int output_index_to_modify = (gid * output_num_slots) + random_index_to_modify;
            seedIndex=Random.setIndex(++seedIndex,  1000);
            
            // save the current word incase the score doesn't improve so that we can revert back to this word
            int hold_word = output[output_index_to_modify];

            // modify current output queue
            output[output_index_to_modify] = findNewOutputWord(output_index_to_modify, data_num_cols, output, seeds, seedIndex);
            seedIndex=seedIndex+3;
            seedIndex=Random.setIndex(seedIndex,  1000);
            
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
                output[output_index_to_modify] = hold_word;
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
