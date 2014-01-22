package com.genetic.ai.kernels;

import com.genetic.ai.kernels.Word;
import junit.framework.TestCase;

/**
 *
 * @author Administrator
 */
public class WordTest extends TestCase {
    
    public WordTest(String testName) {
        super(testName);
    }

    /**
     * Test of createWord method, of class Word.
     */
    public void testCreateWord() {
        Word word = new Word();

        int[] seeds = new int[6];
        seeds[0] = 0;
        seeds[1] = 0;
        seeds[2] = 1;
        seeds[3] = 1;
        seeds[4] = 2;
        seeds[5] = 2;
        
        assertEquals(100, Word.createWord(seeds, 0, 0));
        assertEquals(110, Word.createWord(seeds, 2, 0));
        assertEquals(120, Word.createWord(seeds, 4, 0));
    }

    /**
     * Test of findHold method, of class Word.
     */
    public void testFindHold() {
        assertEquals(0, Word.findHold(0));
        assertEquals(1, Word.findHold(100));
        assertEquals(2, Word.findHold(200));
        assertEquals(9, Word.findHold(999));
        assertEquals(999, Word.findHold(99999));
    }

    /**
     * Test of findAction method, of class Word.
     */
    public void testFindAction() {
        assertEquals(0, Word.findAction(101));
        assertEquals(1, Word.findAction(111));
        assertEquals(2, Word.findAction(121));
        assertEquals(9, Word.findAction(9999));
        assertEquals(0, Word.findAction(0));
    }

    /**
     * Test of findDataIndex method, of class Word.
     */
    public void testFindDataIndex() {
        assertEquals(0, Word.findDataIndex(110));
        assertEquals(1, Word.findDataIndex(111));
        assertEquals(2, Word.findDataIndex(112));
        assertEquals(9, Word.findDataIndex(1199));
        assertEquals(0, Word.findDataIndex(0));
    }
}
