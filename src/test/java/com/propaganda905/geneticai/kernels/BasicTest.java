package com.propaganda905.geneticai.kernels;

import junit.framework.TestCase;

public class BasicTest extends TestCase {
    
    public BasicTest(String testName) {
        super(testName);
    }

    public void testSetup() {
        Basic instance = new Basic();
        instance.setup();
        assertTrue( true );
    }

    public void testRun() {
        Basic kernel = new Basic();
        kernel.setup();
        kernel.run();
        float result = kernel.result[0];
        float expected = 5;
        assertEquals("Basic Kernel", expected, result);
    }
}
