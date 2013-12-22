/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.propaganda905.geneticai.kernels;

import junit.framework.TestCase;

/**
 *
 * @author Administrator
 */
public class MathTest extends TestCase {
    
    public MathTest(String testName) {
        super(testName);
    }
    /**
     * Test of findfindSum method, of class Math.
     */
    public void testfindSum() {
        int doit = 0;
        float[] data = new float[3];
        int[] output = new int[5];
        
//        for(int i=0; i < 100000;i++){
//            data = new float[3];
//            data[0] = 0 + (int)(java.lang.Math.random() * ((1000 - 0) + 1));
//            data[1] = 0 + (int)(java.lang.Math.random() * ((1000 - 0) + 1));
//            data[2] = data[0] + data[1];
//            doit = 0 + (int)(java.lang.Math.random() * ((1 - 0) + 1));
//            
//            output = new int[5];
//            
//        }
        
        data[0] = 1;
        data[1] = 2;
        data[2] = 3;
        output[0]=100;
        output[1]=101;
        assertEquals((float)3.0, Math.findSum(5, 0, output, data, 0));

        output[0]=101;
        output[1]=100;
        assertEquals((float)3.0, Math.findSum(5, 0, output, data, 0));
        
        output[0]=101;
        output[4]=100;
        assertEquals((float)4.0, Math.findSum(5, 0, output, data, 0));
        
        output[2]=101;
        output[4]=100;
        assertEquals((float)6.0, Math.findSum(5, 0, output, data, 0));
        
        output[3]=101;
        output[4]=100;
        assertEquals((float)8.0, Math.findSum(5, 0, output, data, 0));
    }
    
    /**
     * Test of findfindSumOfAction method, of class Math.
     */
    public void testfindSumOfAction() {
        assertEquals((float)20.0, Math.findSumOfAction(10, 0, 10));
        assertEquals((float)0.0, Math.findSumOfAction(10, 1, 10));
        assertEquals((float)100.0, Math.findSumOfAction(10, 2, 10));
        assertEquals((float)1.0, Math.findSumOfAction(10, 3, 10));
        assertEquals((float)10.0, Math.findSumOfAction(0, 0, 10));
        assertEquals((float)-10.0, Math.findSumOfAction(0, 1, 10));
        assertEquals((float)0.0, Math.findSumOfAction(0, 2, 10));
        assertEquals((float)0.0, Math.findSumOfAction(0, 3, 10));
        assertEquals((float)10.0, Math.findSumOfAction(10, 0, 0));
        assertEquals((float)10.0, Math.findSumOfAction(10, 1, 0));
        assertEquals((float)0.0, Math.findSumOfAction(10, 2, 0));
        assertEquals((float)10.0, Math.findSumOfAction(10, 3, 0));
    }
    
    /**
     * Test of findDiff method, of class Math.
     */
    public void testFindDiff() {
        assertEquals((float)90.0, Math.findDiff(100,10));
        assertEquals((float)110.0, Math.findDiff(100,-10));
        assertEquals((float)110.0, Math.findDiff(-100,10));
        assertEquals((float)90.0, Math.findDiff(-100,-10));
        assertEquals((float)10.0, Math.findDiff(0,10));
        assertEquals((float)10.0, Math.findDiff(10,0));
        assertEquals((float)0.0, Math.findDiff(0,0));
    }

    /**
     * Test of run method, of class Math.
     */
    public void testRun() {
        assertTrue( true );
    }
}
