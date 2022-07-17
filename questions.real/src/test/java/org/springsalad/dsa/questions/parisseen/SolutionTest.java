package org.springsalad.dsa.questions.parisseen;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

    Solution slt;

    @BeforeEach
    public  void setup() {
        slt = new Solution();
    }

    @Test
    @Tag("functional test")
    public void testAscendingNumbers() {
        var input = new int[] {1, 2, 4, 5, 6};
        var expect = 4;
        var actual =  slt.findPairs(input);
        assertEquals(expect, actual);
   }

    @Test
    @Tag("functional test")
    public void testWaveNumbers() {
        var input = new int[] {8, 2, 4, 5, 6};
        var expect = 7;
        var actual =  slt.findPairs(input);
        assertEquals(expect, actual);
    }

    @Test
    @Tag("functional test")
    public void testMultipleWavesNumbers() {
        var input = new int[] {8, 2, 9, 4, 7, 15, 3, 6};
        var expect = 2 + 1 + 3 + 1 + 1 + 2 + 1;
        var actual =  slt.findPairs(input);
        assertEquals(expect, actual);
    }
   @Test
   @Tag ("edgeTest")
   public void testNullArray() {
        var actual = slt.findPairs(null);
        assertEquals(0, actual);

   }

    @Test
    @Tag ("edgeTest")
    public void testEmptyArray() {
        var actual = slt.findPairs(new int[0]);
        assertEquals(0, actual);

    }
}
