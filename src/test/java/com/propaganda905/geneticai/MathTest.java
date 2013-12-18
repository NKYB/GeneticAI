/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.propaganda905.geneticai;

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
     * Test of add method, of class Math.
     */
    public void testAdd() {
        System.out.println("add");
        int expResult = 5;
        int result = Math.add();
        assertEquals(expResult, result);
    }
}
