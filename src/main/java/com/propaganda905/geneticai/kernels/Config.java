package com.propaganda905.geneticai.kernels;

/**
 *
 * @author Administrator
 */
public class Config {
    private int[] config = new int[6];
    
    /**
     * number of input lines
     */
    private int data_num_rows = 20;
    
    /**
     * number of input columns
     */
    private int data_num_cols = 2;
    
    /**
     * number of output slots
     */
    private int output_num_slots = 17;    
    
    /**
     * number of kernel executers
     */
    private int num_kernels = 1;
    
    /**
     * number of slots for output stats
     */
    private int output_stats_slots = 20;
    
    /**
     * number of generations to run
     */
    private int generation_num = 10000;
    
    public int[] getConfig(){
        return config;
    }

    public int getGeneration_num() {
        return generation_num;
    }

    public void setGeneration_num(int generation_num) {
        config[5] = generation_num;
        this.generation_num = generation_num;
    }

    public int getData_num_rows() {
        return data_num_rows;
    }

    public void setData_num_rows(int data_num_rows) {
        config[0] = data_num_rows;
        this.data_num_rows = data_num_rows;
    }

    public int getData_num_cols() {
        return data_num_cols;
    }

    public void setData_num_cols(int data_num_cols) {
        config[1] = data_num_cols;
        this.data_num_cols = data_num_cols;
    }

    public int getOutput_num_slots() {
        return output_num_slots;
    }

    public void setOutput_num_slots(int output_num_slots) {
        config[2] = output_num_slots;
        this.output_num_slots = output_num_slots;
    }

    public int getNum_kernels() {
        return num_kernels;
    }

    public void setNum_kernels(int num_kernels) {
        config[3] = num_kernels;
        this.num_kernels = num_kernels;
    }

    public int getOutput_stats_slots() {
        return output_stats_slots;
    }

    public void setOutput_stats_slots(int output_stats_slots) {
        config[4] = output_stats_slots;
        this.output_stats_slots = output_stats_slots;
    }

}
