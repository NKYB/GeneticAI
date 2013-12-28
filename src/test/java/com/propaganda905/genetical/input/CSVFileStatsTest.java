/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.propaganda905.genetical.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import junit.framework.TestCase;

/**
 *
 * @author Administrator
 */
public class CSVFileStatsTest extends TestCase {
    
    private String fullFileNameAndPath;
    
    public CSVFileStatsTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        URL resourceUrl = getClass().getResource("/testdata.csv");
        Path resourcePath = Paths.get(resourceUrl.toURI());
        this.fullFileNameAndPath = resourcePath.toString();
    }

    /**
     * Test of getCountLines method, of class CSVFileStats.
     */
    public void testGetCountLines() throws FileNotFoundException {
        CSVFileStats csvFileStats = new CSVFileStats();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fullFileNameAndPath));
        csvFileStats.setFileStats(bufferedReader, 10);
        assertEquals(5, csvFileStats.getCountLines());

        csvFileStats = new CSVFileStats();
        bufferedReader = new BufferedReader(new FileReader(this.fullFileNameAndPath));
        csvFileStats.setFileStats(bufferedReader, 3);
        assertEquals(3, csvFileStats.getCountLines());
    }

    /**
     * Test of getMaxColumns method, of class CSVFileStats.
     */
    public void testGetMaxColumns() throws FileNotFoundException {
        CSVFileStats csvFileStats = new CSVFileStats();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fullFileNameAndPath));
        csvFileStats.setFileStats(bufferedReader, 10);
        assertEquals(4, csvFileStats.getMaxColumns());
    }

    /**
     * Test of setFileStats method, of class CSVFileStats.
     */
    public void testSetFileStats() throws FileNotFoundException {
        CSVFileStats csvFileStats = new CSVFileStats();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fullFileNameAndPath));
        csvFileStats.setFileStats(bufferedReader, 10);
        assertEquals(5, csvFileStats.getCountLines());
        assertEquals(4, csvFileStats.getMaxColumns());
    }
}
