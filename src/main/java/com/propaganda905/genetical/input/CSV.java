/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.propaganda905.genetical.input;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class CSV extends InputObject {
    public void readCSVFileFromDisk(String fullFileNameAndPath, int maxLines){
        BufferedReader bufferReader = null;
        String line;
        String seperator = ",";
        int countLines;
        
        try{
            bufferReader = new BufferedReader(new FileReader(fullFileNameAndPath));
            
            CSVFileStats csvFileStats = new CSVFileStats();
            csvFileStats.setFileStats(bufferReader, maxLines);
            
            this.initData(csvFileStats.getCountLines(), csvFileStats.getMaxColumns());
            
            countLines = 0;
            bufferReader = new BufferedReader(new FileReader(fullFileNameAndPath));
            while((line = bufferReader.readLine()) != null && countLines < maxLines){
                if (line.split(seperator).length ==  csvFileStats.getMaxColumns()){
                    addLineToData(line, countLines);
                    countLines++;
                }
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            if (bufferReader != null){
                try{
                    bufferReader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    

    
    private void addLineToData(String line, int row){
        int column = 0; 
        String seperator = ",";
        String[] dataLine = line.split(seperator);
        for (String dataPoint : dataLine){
            if (isFloat(dataPoint)){
                this.setData(row, column, stringToFloat(dataPoint));
                column++;
            }
        }
        
    }
    
    private Float stringToFloat(String suspect){
        Float result = -10000000F;
        if (isFloat(suspect)){
            result = Float.valueOf(suspect);
        }
        return result;
    }
    
    private boolean isFloat(Object suspect){
        boolean result = false;
        try{
            Float.valueOf((String)suspect);
            result = true;
        } catch(NumberFormatException e){
            result = false;
        }
        return result;
    }
}
