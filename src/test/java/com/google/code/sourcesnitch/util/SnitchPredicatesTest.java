
package com.google.code.sourcesnitch.util;

import com.google.code.sourcesnitch.io.SourceFile;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Nick Faulkner
 */
@RunWith(MockitoJUnitRunner.class)
public class SnitchPredicatesTest {

    @Mock SourceFile mockSourceFile;

    @Test(expected=NullPointerException.class)
    public void testContainsThrowsIfPassedNull() {
        SnitchPredicates.contains(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testContainsThrowsIfPassedEmpty() {
        SnitchPredicates.contains("");
    }

    @Test
    public void testContainsReturnsExpectedPredicate() {
        when(mockSourceFile.readFully()).thenReturn("test     test");

        assertTrue(SnitchPredicates.contains("   ").apply(mockSourceFile));
        assertTrue(SnitchPredicates.contains("t").apply(mockSourceFile));
        assertTrue(SnitchPredicates.contains("test ").apply(mockSourceFile));

        assertFalse(SnitchPredicates.contains("tEst ").apply(mockSourceFile));
        assertFalse(SnitchPredicates.contains("      ").apply(mockSourceFile));
    }

    @Test(expected=NullPointerException.class)
    public void testMatchedThrowsIfPassedNull() {
        SnitchPredicates.matches(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testMatchedThrowsIfPassedEmpty() {
        SnitchPredicates.matches("");
    }

    @Test
    public void testMatchesReturnsExpectedPredicate() {
        when(mockSourceFile.readFully()).thenReturn("test     test");

        assertTrue(SnitchPredicates.matches("test     test").apply(mockSourceFile));
        assertTrue(SnitchPredicates.matches(".*   .*").apply(mockSourceFile));
        assertTrue(SnitchPredicates.matches(".*t.*").apply(mockSourceFile));
        assertTrue(SnitchPredicates.matches("test .*").apply(mockSourceFile));
        assertTrue(SnitchPredicates.matches(".*").apply(mockSourceFile));
        assertTrue(SnitchPredicates.matches(".*\\w+.*").apply(mockSourceFile));
        assertTrue(SnitchPredicates.matches("^test  .*").apply(mockSourceFile));

        assertFalse(SnitchPredicates.matches("tEst ").apply(mockSourceFile));
        assertFalse(SnitchPredicates.matches("      ").apply(mockSourceFile));
        assertFalse(SnitchPredicates.matches("$\\w+").apply(mockSourceFile));
    }
}
