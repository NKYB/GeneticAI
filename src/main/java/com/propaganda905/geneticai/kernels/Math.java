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
    public float[] population_scores;
    public int[] population_indexs;
    private int[] found_answer;
    
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
        
        this.population_scores = Config.getPopulationScores(num_kernels);
        this.population_indexs = Config.getPopulationIndexs(num_kernels);
        
        this.found_answer = Config.getFoundAnswer();
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
        int countFailed = 0;
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
            
//            outputResult(
//                        data, 
//                        output, 
//                        hold_word, 
//                        output_index_to_modify, 
//                        output[output_index_to_modify], 
//                        sub_score, 
//                        score
//                    );

            if (sub_score < score){
                score = sub_score;
                
                /**
                 * Experimental inter kernel communication
                 */
//                int flagContinue = 1;
//                for(int q=0; q < num_kernels;q++){
//                    if (sub_score < this.population_scores[q] && flagContinue == 1){
//                        this.population_scores[q] = sub_score;
//                        this.population_indexs[q] = gid;
//                        flagContinue = 0;
//                    } else if (sub_score == this.population_scores[q]){
//                        flagContinue = 0;
//                    }
//                }
            
            } else {
                output[output_index_to_modify] = hold_word;
                
                countFailed++;
                
                if (countFailed > 1000){
                    countFailed = 0;
                    for(int k=0; k < output_num_slots;k++){
                        output_stats[(gid * output_stats_slots)+k] = 0;
                    }
                    score = 100000;
                    
                }
                    

                    /**
                    * Experimental inter kernel communication
                    */
//                    int random_winner = Random.next(0,  10, seeds, seedIndex) * 1;
//                    seedIndex=Random.setIndex(++seedIndex,  1000);
//                    for(int k=0; k < output_num_slots;k++){
//                        output_stats[(this.population_indexs[random_winner] * output_num_slots) + k] = output[(this.population_indexs[random_winner] * output_num_slots) + k];
//                    }
//                    score = this.population_scores[random_winner];
//                    
//                }     

            }
//if (score == 0){
            if (score == 0 || found_answer[0] == 1){
                output_stats[(gid * output_stats_slots)] = i;
                
                if (found_answer[0] == 1){
                    output_stats[(gid * output_stats_slots)] = i ;
                }
                if (score == 0){
                    found_answer[0] = 1;
                }
                
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
    
//    public void outputResult(float[] data, int[] output_stats, int hold_word, int output_index_to_modify, int word, float sub_score, float score){
//        int foundAnswerFlag = 0;
//        int countKernels = 0;
//        float sum = 0;
//        for (int i = 0; i < num_kernels * output_num_slots; i++) {
//
//            if (output_stats[i] > 0){
//                if (foundAnswerFlag == 0){
//                    System.out.println( "---- index to modify: " + output_index_to_modify + " word: " + word);
//                }
//                foundAnswerFlag = 1;
//                System.out.println( "  Word[" + i + "]: " + toString((int)output_stats[i], data, sum) );
//                sum = wordToNum(sum, (int)output_stats[i], data);
//            }
//            if ((i%output_num_slots==output_num_slots-1) && (foundAnswerFlag == 1)){
//                System.out.println( "  Sum    : " + sum);
//                System.out.println( "  Target : " + data[data_num_cols]);
//                System.out.println( "  Sub Score : " + sub_score);
//                System.out.println( "  Score : " + score);
//                foundAnswerFlag = 0;
//                sum=0;
//            }
//        }
//    }
//    
//    public float wordToNum(float sum, int word, float[] data){
//        int action = Word.findAction(word);
//        int dataIndex = Word.findDataIndex(word);
//        return Math.findSumOfAction(sum, action, data[dataIndex]);
//    }
//    
//    public String toString(int word, float[] data, float sum){
//        int action = Word.findAction(word);
//        int dataIndex = Word.findDataIndex(word);
//        String actionPhrase = "";
//        if (action == 0){
//            actionPhrase = "+";
//        } else if (action == 1){
//            actionPhrase = "-";
//        } else if (action == 2){
//            actionPhrase = "*";
//        } else if (action == 3){
//            actionPhrase = "/";
//        }
//        return sum + " " + actionPhrase + " " +  data[dataIndex] + " (" + word + ")";
//    }
}
