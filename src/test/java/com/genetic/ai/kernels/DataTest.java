package com.propaganda905.geneticai.kernels;

import com.genetic.ai.kernels.Data;
import junit.framework.TestCase;

/**
 *
 * @author Administrator
 */
public class DataTest extends TestCase {
    
    public DataTest(String testName) {
        super(testName);
    }

    /**
     * Test of getNumRows method, of class Data.
     */
    public void testGetNumRows() {
        Data data = new Data();
        data.createRandomDataAsMap(100, 15);
        int expResult = 100;
        int result = data.getNumRows();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNumRows method, of class Data.
     */
    public void testSetNumRows() {
        int numRows = 5;
        Data data = new Data();
        data.createRandomDataAsMap(numRows, 5);
        data.setNumRows(numRows);
        assertEquals(numRows, data.getNumRows());
    }

    /**
     * Test of getNumCols method, of class Data.
     */
    public void testGetNumCols() {
        int numCols = 40;
        Data data = new Data();
        data.createRandomDataAsMap(100, numCols);
        assertEquals(numCols, data.getNumCols());
    }

    /**
     * Test of getNumDataCols method, of class Data.
     */
    public void testGetNumDataCols() {
        int numCols = 43;
        Data data = new Data();
        data.createRandomDataAsMap(100, numCols);
        assertEquals(numCols-1, data.getNumDataCols());
    }

    /**
     * Test of setNumCols method, of class Data.
     */
    public void testSetNumCols() {
         int numCols = 499;
        Data data = new Data();
        data.createRandomDataAsMap(100, numCols);
        data.setNumCols(numCols);
        assertEquals(numCols, data.getNumCols());
    }

    /**
     * Test of getData method, of class Data.
     */
    public void testGetData() {
        Data data = new Data();
        data.createRandomDataAsMap(1000, 26);
        float[] result = data.getData();
        assertEquals(1000 * 26, result.length);
    }

    /**
     * Test of getDataAtPoint method, of class Data.
     */
    public void testGetDataAtPoint() {
        Data data = new Data();
        data.createRandomDataAsMap(100, 6);
        
        float value = data.getDataAtPoint(0, 0);
        if (value < 0 && value > 10000){
            fail("GetDataAtPoint Problem 0 0");
        }
        
        value = data.getDataAtPoint(99, 5);
        if (value < 0 && value > 10000){
            fail("GetDataAtPoint Problem 99 5");
        }
    }

    /**
     * Test of setData method, of class Data.
     */
    public void testSetData() {
        Data data = new Data();
        data.createRandomDataAsMap(100, 6);
        
        Data data2 = new Data();
        data2.setData(data.getData());
        assertEquals(data.getData().length, data2.getData().length);
    }

    /**
     * Test of createDataAsMap method, of class Data.
     */
    public void testCreateDataAsMap() {
        float[][] d = new float[2][3];
        d[0][0] = 1;
        d[0][1] = 2;
        d[0][2] = 3;
        d[1][0] = 4;
        d[1][1] = 5;
        d[1][2] = 9;
        
        Data data = new Data();
        data.setNumCols(2);
        data.createDataAsMap(d);
        
        assertEquals(d.length * d[0].length, data.getData().length);
    }

    /**
     * Test of createRandomDataAsMap method, of class Data.
     */
    public void testCreateRandomDataAsMap() {
        Data data = new Data();
        data.createRandomDataAsMap(100, 6);
        assertEquals(100 * 6, data.getData().length);
    }
}
