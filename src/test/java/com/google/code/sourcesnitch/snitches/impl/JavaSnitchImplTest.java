
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
import static com.google.code.sourcesnitch.TeztHelper.*;

/**
 *
 * @author Nick Faulkner
 */
@RunWith(MockitoJUnitRunner.class)
public class JavaSnitchImplTest {

    @Mock SourceFile mockSourceFileOne;
    @Mock SourceFile mockSourceFileTwo;
    @Mock SourceFile mockSourceFileThree;

    private JavaSnitchImpl snitch;

    @Before
    public void setUp() throws Exception {
        when(mockSourceFileOne.getFileName()).thenReturn("TestOne.java");
        when(mockSourceFileOne.readFully()).thenReturn(contentsOfTestFile("TestOne.java"));

        when(mockSourceFileTwo.getFileName()).thenReturn("TestTwo.java");
        when(mockSourceFileTwo.readFully()).thenReturn(contentsOfTestFile("TestTwo.java"));

        when(mockSourceFileThree.getFileName()).thenReturn("TestThree.java");
        when(mockSourceFileThree.readFully()).thenReturn(contentsOfTestFile("TestThree.java"));

        snitch = new JavaSnitchImpl(Sets.newLinkedHashSet(Lists.newArrayList(mockSourceFileOne, mockSourceFileTwo, mockSourceFileThree)));
    }

    @Test
    public void testImportPackagePass() {
        String failureMessage = null;
        try {
            snitch.importPackage("java.util.concurrent");
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertNull(failureMessage);
    }

    @Test
    public void testImportPackageFailure() {
        String failureMessage = null;
        try {
            snitch.importPackage("java.util.regex");
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertEquals("Files found that do not import package 'java.util.regex': TestOne.java", failureMessage);
    }

    @Test
    public void testDoNotImportPackagePass() {
        String failureMessage = null;
        try {
            snitch.doNotImportPackage("java.sql");
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertNull(failureMessage);
    }

    @Test
    public void testDoNotImportPackageFailure() {
        String failureMessage = null;
        try {
            snitch.doNotImportPackage("com.sun.corba");
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertEquals("Files found that import package 'com.sun.corba': TestOne.java", failureMessage);
    }

    @Test
    public void testDoNotWriteToSytemOutOrErrPass() {
        String failureMessage = null;
        try {
            new JavaSnitchImpl(Sets.newHashSet(mockSourceFileTwo)).doNotWriteToSytemOutOrErr();
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertNull(failureMessage);
    }

    @Test
    public void testDoNotWriteToSytemOutOrErrFailure() {
        String failureMessage = null;
        try {
            snitch.doNotWriteToSytemOutOrErr();
        } catch (SnitchAssertionFailure saf) {
            failureMessage = saf.getMessage();
        }

        assertEquals("Files found that print to system: TestOne.java, TestThree.java", failureMessage);
    }
}
