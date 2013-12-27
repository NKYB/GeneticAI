/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.propaganda905.geneticai.input;

import com.propaganda905.genetical.input.CSV;
import java.io.File;
import java.io.IOException;
import junit.framework.TestCase;

/**
 *
 * @author Administrator
 */
public class CSVTest extends TestCase {
    
    public CSVTest(String testName) {
        super(testName);
    }

    /**
     * Test of readCSVFileFromDisk method, of class CSV.
     */
    public void testReadCSVFileFromDisk() throws IOException {
        System.out.println("readCSVFileFromDisk");
        String currentPath = new File(".").getCanonicalPath();
        String fullFileNameAndPath = currentPath + "/src/test/java/com/propaganda905/geneticai/input/testdata.csv";
        CSV csvInputObject = new CSV();
        csvInputObject.readCSVFileFromDisk(fullFileNameAndPath, 6);
        
        float[][] data = csvInputObject.getData();
        
        assertEquals(5, data.length);
        assertEquals(4, data[0].length);
    }
}
