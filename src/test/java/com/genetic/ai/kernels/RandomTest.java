/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genetic.ai.kernels;

import com.genetic.ai.kernels.Random;
import junit.framework.TestCase;

/**
 *
 * @author Administrator
 */
public class RandomTest extends TestCase {
    
    /**
     * Test Setup 
     * 
     * @param testName 
     */
    public RandomTest(String testName) {
        super(testName);
    }
    
    

    /**
     * Test of setIndex method, of class Random.
     */
    public void testSetIndex() {
        int index = 0;
        int maxLength = 3;
        int result = Random.setIndex(index, maxLength);
        assertEquals(0, result);
        result = Random.setIndex(++index, maxLength);
        assertEquals(1, result);
        result = Random.setIndex(++index, maxLength);
        assertEquals(2, result);
        result = Random.setIndex(++index, maxLength);
        assertEquals(0, result);
        
        Random rnd = new Random();
        assertEquals(0, rnd.nothing());
    }

    /**
     * Test of next method, of class Random.
     */
    public void testNext() {
        int min = 0;
        int max = 10;
        int[] seeds = new int[5];
        seeds[0] = 0;
        seeds[1] = 10;
        seeds[2] = 15;
        seeds[3] = 1;
        seeds[4] = 5;

        assertEquals(0, Random.next(min, max, seeds, 0));
        assertEquals(10, Random.next(min, max, seeds, 1));
        assertEquals(4, Random.next(min, max, seeds, 2));
        assertEquals(1, Random.next(min, max, seeds, 3));
        assertEquals(5, Random.next(min, max, seeds, 4));
    }
}
