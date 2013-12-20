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
        app.runMathKernel();
        assertTrue( true );
    }
    
}
