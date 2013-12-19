package com.propaganda905.geneticai;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{
    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        TestSuite suite= new TestSuite();
        suite.addTestSuite( AppTest.class );
        return suite;
    }

    public void testApp()
    {
        App app = new App();
        app.main(new String[0]);
        assertTrue( true );
    }
    
}
