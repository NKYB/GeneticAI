package com.propaganda905.geneticai;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
        App.main(new String[0]);
        assertTrue( true );
    }
    
}
