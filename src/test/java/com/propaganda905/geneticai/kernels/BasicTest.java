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
        assertEquals("Basic Kernel", 5, kernel.result[0]);
    }
}
