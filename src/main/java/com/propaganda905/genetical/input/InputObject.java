/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.propaganda905.genetical.input;

/**
 *
 * @author Administrator
 */
abstract class InputObject {
    private float[][] data;
    
    public float[][] getData(){
        return this.data;
    }
    
    void setData(int row, int column, float value){
        this.data[row][column] = value;
    }
    
    void initData(int numRows, int numColumns){
        this.data = new float[numRows][numColumns];
    }
}
