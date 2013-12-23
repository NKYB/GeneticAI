/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.propaganda905.geneticai.kernels;

/**
 *
 * @author Administrator
 */
public class Word {
    public static int createWord(int[] seeds, int seedIndex){
        int action = Random.next(0, 3, seeds, seedIndex);
        seedIndex=Random.setIndex(++seedIndex, 1000);
        int dataIndex = Random.next(0, 1, seeds, seedIndex);
//        seedIndex=Random.setIndex(++seedIndex, 1000);
        int word = 100;
        word = word + (action * 10);
        word = word + dataIndex;
        return word;
    }
    
    static int findHold(int word){
        return (word - (word % 100)) / 100;
    }
    
    public static int findAction(int word){
        return (word - (findHold(word)*100)) / 10;
    }
    
    public static int findDataIndex(int word){
        return word % 10;
    }
}
