package com.propaganda905.geneticai.kernels;

import com.amd.aparapi.Kernel;

public class Math extends Kernel {

    public Math(float[] data, int output[], int[] seeds, int[] config) {
        this.data = data.clone();
        this.output = output.clone();
        this.seeds = seeds.clone();

        data_num_rows = config[0];
        data_num_cols = config[1];
        output_num_slots = config[2];
    }
    
    //config
    private final int data_num_rows;
    private final int data_num_cols;
    private final int output_num_slots;

    private float[] data;
    public int[] output;
    private int[] seeds;


    @Override
    public void run() {
        // preseed the random number index
        int gid = getGlobalId();
        int seedIndex=Random.setIndex(gid, 1000);
        float score = 100000;
        
        for(int i=0; i < 1000000;i++){
            
            //evolve
            int index = Random.next(0, 20, seeds, seedIndex);
            seedIndex=Random.setIndex(++seedIndex,  1000);
            
            //word
            
            int word = 100;
            word = word + Random.next(0, 3, seeds, seedIndex) * 10;
            seedIndex=Random.setIndex(++seedIndex,  1000);
            word = word + Random.next(0, 1, seeds, seedIndex) * 1;
            seedIndex=Random.setIndex(++seedIndex,  1000);
            
            int change_method = Random.next(0, 2, seeds, seedIndex) * 1;
            seedIndex=Random.setIndex(++seedIndex,  1000);
            int word_holder = output[(gid * output_num_slots) + index];
            if (change_method < 2){
                output[(gid * output_num_slots) + index] = word;
            } else {
                output[(gid * output_num_slots) + index] = 0;
            }
            
            //eval
            
            float sub_score = 100000;
            for(int j=0; j < data_num_rows;j=j+data_num_cols+1){
                float a = data[j];
                float b = data[j+1];
                float t = data[j+2];
                
                float sum = 0;
                for(int k=0; k < output_num_slots;k++){
                    int word_temp = output[(gid * output_num_slots) + k];
                    if (word_temp > 99){
                        int hold = ((word_temp - (word_temp % 100)) / 100);
                        int action = (word_temp - (hold*100)) / 10;
                        int dataIndex = (word_temp % 10);
                        float dataValue = 0;
                        if (dataIndex == 0){
                            dataValue = a;
                        } else if (dataIndex == 1){
                            dataValue = b;
                        }
                        if (action == 0){
                            sum = sum + dataValue;
                        } else if (action == 1){
                            sum = sum - dataValue;
                        } else if (action == 2){
                            sum = sum * dataValue;
                        } else if (action == 3){
                            if (sum != 0){
                                if (dataValue != 0){
                                    sum = sum / dataValue;
                                }
                            }
                        }
                    }
                }
                if (t >= sum){
                    sub_score = t - sum;
                } else {
                    sub_score = sum - t;
                }
            }
            if (sub_score < score){
                score = sub_score;
            } else {
                output[(gid * output_num_slots) + index] = word_holder;
            }
            if (score == 0){
                i=1000000;
            }
        }
        
        
        
//        int program[] = new int[20];
        
//        float score = 0;
//        for (int m = 0; m < 20; m++) {
//            float value = inputData[m]
//            float sum = 0;
//        }
        
//        int rndIndex = Random.next(0, 10, seeds, seedIndex);
//        seedIndex=Random.setIndex(++seedIndex,  1000);
        
//        output[gid * output_num_slots] = word;
    }
   
}
