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
        float[] d = new float[3];
        int[] o;
        
        d[0] = 1;
        d[1] = 2;
        d[2] = 3;
        
        o = new int[5]; o[0]=100; o[1]=101; o[2]=0; o[3]=0; o[4]=0;
        assertEquals((float)3.0, Math.findSum(5, 3, 0, o, d, 0));

        o = new int[5]; o[0]=101; o[1]=100; o[2]=0; o[3]=0; o[4]=0;
        assertEquals((float)3.0, Math.findSum(5, 3, 0, o, d, 0));
        
        o = new int[5]; o[0]=101; o[1]=0; o[2]=0; o[3]=0; o[4]=100;
        assertEquals((float)3.0, Math.findSum(5, 3, 0, o, d, 0));
        
        o = new int[5]; o[0]=101; o[1]=0; o[2]=0; o[3]=100; o[4]=0;
        assertEquals((float)3.0, Math.findSum(5, 3, 0, o, d, 0));
        
        o = new int[5]; o[0]=0; o[1]=0; o[2]=0; o[3]=101; o[4]=100;
        assertEquals((float)3.0, Math.findSum(5, 3, 0, o, d, 0));
        
        o = new int[5]; o[0]=111; o[1]=0; o[2]=0; o[3]=0; o[4]=0;
        assertEquals((float)-2.0, Math.findSum(5, 3, 0, o, d, 0));
        
        o = new int[5]; o[0]=111; o[1]=110; o[2]=0; o[3]=0; o[4]=0;
        assertEquals((float)-3.0, Math.findSum(5, 3, 0, o, d, 0));
        
        o = new int[5]; o[0]=0; o[1]=130; o[2]=121; o[3]=100; o[4]=110;
        assertEquals((float)0.0, Math.findSum(5, 3, 0, o, d, 0));
        
        o = new int[5]; o[0]=100; o[1]=101; o[2]=101; o[3]=111; o[4]=120;
        assertEquals((float)3.0, Math.findSum(5, 3, 0, o, d, 0));
        
        d = new float[3]; d[0] = 22; d[1] = 29; d[2] = 638;
        o = new int[5]; o[0]=100; o[1]=100; o[2]=121; o[3]=0; o[4]=0;
        assertEquals((float)1276.0, Math.findSum(5, 3, 0, o, d, 0));
        
        d = new float[3]; d[0] = 30; d[1] = 2; d[2] = 60;
        o = new int[5]; o[0]=110; o[1]=100; o[2]=121; o[3]=0; o[4]=0;
        assertEquals((float)0.0, Math.findSum(5, 3, 0, o, d, 0));
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
