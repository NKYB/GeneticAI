package com.propaganda905.geneticai;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Test the application entry point 
 * 
 * @author NKYB
 */
public class AppTest extends TestCase{
    
    /**
     * Test Setup 
     * 
     * @param testName
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * Test Suite 
     * 
     * @return suite
     */
    public static Test suite()
    {
        TestSuite suite= new TestSuite();
        suite.addTestSuite( AppTest.class );
        return suite;
    }

    /**
     * Basic test of main with no args 
     */
    public void testApp()
    {
        App.main(new String[0]);
        App app = new App();
        app.runBasicKernel();
        app.runMathKernel(3);
        app.runMathKernel(300);
        app.runMathKernel(30000);
        assertTrue( true );
    }

    /**
     * Test of main method, of class App.
     */
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        App.main(args);
        assertTrue( true );
    }

    /**
     * Test of runBasicKernel method, of class App.
     */
    public void testRunBasicKernel() {
        System.out.println("runBasicKernel");
        App instance = new App();
        instance.runBasicKernel();
        assertTrue( true );
    }

    /**
     * Test of runMathKernel method, of class App.
     */
    public void testRunMathKernel() {
        System.out.println("runMathKernel");
        App instance = new App();
        instance.runMathKernel(10000);
        assertTrue( true );
    }

    /**
     * Test of wordToNum method, of class App.
     */
    public void testWordToNum() {
        System.out.println("wordToNum");
        float sum = 0.0F;
        int word = 100;
        float[] data = new float[3];
        data[0] = 1;
        data[1] = 2;
        data[2] = 3;
        App instance = new App();
        float expResult = 1.0F;
        float result = instance.wordToNum(sum, word, data);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of toString method, of class App.
     */
    public void testToString() {
        float[] data = new float[3];
        data[0] = 1;
        data[1] = 2;
        data[2] = 3;
        App instance = new App();
        assertEquals("0.0 + 1.0 (100)", instance.toString(100, data, 0));
        assertEquals("0.0 + 2.0 (101)", instance.toString(101, data, 0));
        assertEquals("10.0 - 2.0 (111)", instance.toString(111, data, 10));
        assertEquals("10.0 * 2.0 (121)", instance.toString(121, data, 10));
        assertEquals("10.0 / 2.0 (131)", instance.toString(131, data, 10));
    }
    
}
