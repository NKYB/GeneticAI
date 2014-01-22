/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.propaganda905.geneticai.input;

import com.genetic.ai.input.CSV;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void testReadCSVFileFromDisk() throws IOException, URISyntaxException {
        URL resourceUrl = getClass().getResource("/testdata.csv");
        Path resourcePath = Paths.get(resourceUrl.toURI());
        String fullFileNameAndPath = resourcePath.toString();
        CSV csvInputObject = new CSV();
        csvInputObject.readCSVFileFromDisk(fullFileNameAndPath, 6);
        
        float[][] data = csvInputObject.getData();
        
        assertEquals(5, data.length);
        assertEquals(4, data[0].length);
    }
}
