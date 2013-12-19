package com.propaganda905.geneticai;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite= new TestSuite();
        suite.addTestSuite( AppTest.class );
        return suite;
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        App app = new App();
        App.main(new String[0]);
        app.main(new String[0]);
        assertTrue( true );
    }
    
}
