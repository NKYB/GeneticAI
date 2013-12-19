package com.propaganda905.geneticai;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/* 
 * Test the application entry point 
 * 
 * @author NKYB
 */
public class AppTest extends TestCase{
    
    /* 
     * Test Setup 
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /* 
     * Test Suite 
     */
    public static Test suite()
    {
        TestSuite suite= new TestSuite();
        suite.addTestSuite( AppTest.class );
        return suite;
    }

    /* 
     * Basic test of main with no args 
     */
    public void testApp()
    {
        App.main(new String[0]);
        assertTrue( true );
    }
    
}
