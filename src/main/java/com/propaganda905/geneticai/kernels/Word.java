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
    static int createWord(int[] seeds, int seedIndex){
        int action = Random.next(0, 3, seeds, seedIndex);
        seedIndex=Random.setIndex(++seedIndex,  1000);
        int dataIndex = Random.next(0, 1, seeds, seedIndex);
        seedIndex=Random.setIndex(++seedIndex,  1000);
        int word = 100;
        word = word + (action * 10);
        word = word + dataIndex;
        return word;
    }
}
