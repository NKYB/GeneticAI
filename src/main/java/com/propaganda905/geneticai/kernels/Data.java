/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.propaganda905.geneticai.kernels;

/**
 *
 * @author Administrator
 */
public class Data {
    private float[] data;
    
    private int numRows = 0;

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }
    
    public int getNumDataCols() {
        return numCols-1;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }
    
    private int numCols = 0;

    public float[] getData() {
        return data;
    }
    
    public float getDataAtPoint(int row, int col){
        float[] data = getData();
        return data[row*col+col];
    }

    public void setData(float[] data) {
        this.data = data;
    }
    
    public float[] createDataAsMap(float[][] inputData){
        setNumRows(inputData.length);
        setNumCols(inputData[0].length);
        float[] data = new float[getNumRows() * getNumCols()];
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumCols(); j++) {
                data[i*getNumCols()+j] = inputData[i][j];
            }
        }
        setData(data);
        return getData();
    }
    
    public float[] createRandomDataAsMap(int numRows, int numCols){
        float[][] inputData = new float[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (j<numCols-1){
                    inputData[i][j] = (int)(java.lang.Math.random()*100);
                } else {
                    inputData[i][j] = inputData[i][0] * inputData[i][1] +  inputData[i][2] - inputData[i][3];
                }
            }
        }
        return createDataAsMap(inputData);
    }
}
