/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genetic.ai.input;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class CSVFileStats {
    private int countLines = 0;
    
    private int maxColumns = 0;

    public int getCountLines() {
        return countLines;
    }

    public int getMaxColumns() {
        return maxColumns;
    }

    public void setFileStats(BufferedReader bufferReader, int maxLines){
        String line;
        String seperator = ",";
        int maxColumnsTemp;
        try{
            while((line = bufferReader.readLine()) != null && countLines < maxLines){
                maxColumnsTemp = line.split(seperator).length;
                if (maxColumnsTemp > maxColumns){
                    countLines = 1;
                    maxColumns = maxColumnsTemp;
                } else {
                    countLines++;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
