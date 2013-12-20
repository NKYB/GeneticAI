package com.propaganda905.geneticai.kernels;

import junit.framework.TestCase;

/**
 * Test the basic kernel
 * 
 * @author NKYB
 */
public class BasicTest extends TestCase {
    
    /**
     * Test Setup 
     * 
     * @param testName 
     */
    public BasicTest(String testName) {
        super(testName);
    }

    /**
     * Test Suite 
     */
    public void testSetup() {
        Basic instance = new Basic();
        instance.setup();
        assertTrue( true );
    }

    /**
     * Basic kernel layout
     */
    public void testRun() {
        Basic kernel = new Basic();
        kernel.setup();
        kernel.run();
        float result = kernel.result[0];
        float expected = 5;
        assertEquals("Basic Kernel", expected, result);
    }
}
