
package com.google.code.sourcesnitch.snitches.impl;

import com.google.common.collect.Lists;
import com.google.code.sourcesnitch.exception.SnitchAssertionFailure;
import com.google.code.sourcesnitch.io.SourceFile;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 * @author Nick Faulkner
 */
@RunWith(MockitoJUnitRunner.class)
public class GenericSnitchImplTest {

    @Mock SourceFile mockSourceFileOne;
    @Mock SourceFile mockSourceFileTwo;

    private GenericSnitchImpl snitch;

    @Before
    public void setUp() {
        when(mockSourceFileOne.getFileName()).thenReturn("testone.txt");
        when(mockSourceFileOne.readFully()).thenReturn("test one test");

        when(mockSourceFileTwo.getFileName()).thenReturn("testtwo.txt");
        when(mockSourceFileTwo.readFully()).thenReturn("test two test");

        snitch = new GenericSnitchImpl(Sets.newLinkedHashSet(Lists.newArrayList(mockSourceFileOne, mockSourceFileTwo)));
    }

    @Test
    public void testContainPass() {
        String failureMessage = null;
        try {
            snitch.contain("test");
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertNull(failureMessage);
    }

    @Test
    public void testContainFailure() {
        String failureMessage = null;
        try {
            snitch.contain("two");
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertEquals("Files found that do not contain 'two': testone.txt", failureMessage);
    }

    @Test
    public void testDoNotContainPass() {
        String failureMessage = null;
        try {
            snitch.doNotContain("tezt");
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertNull(failureMessage);
    }

    @Test
    public void testDoNotContainFailure() {
        String failureMessage = null;
        try {
            snitch.doNotContain("two");
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertEquals("Files found that contain 'two': testtwo.txt", failureMessage);
    }

    @Test
    public void testMatchPass() {
        String failureMessage = null;
        try {
            snitch.match("^test.*");
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertNull(failureMessage);
    }

    @Test
    public void testMatchFailure() {
        String failureMessage = null;
        try {
            snitch.match("^test one.*");
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertEquals("Files found that do not match '^test one.*': testtwo.txt", failureMessage);
    }

    @Test
    public void testDoNotMatchPass() {
        String failureMessage = null;
        try {
            snitch.doNotMatch("tezt");
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertNull(failureMessage);
    }

    @Test
    public void testDoNotMatchFailure() {
        String failureMessage = null;
        try {
            snitch.doNotMatch(".*");
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertEquals("Files found that match '.*': testone.txt, testtwo.txt", failureMessage);
    }
}