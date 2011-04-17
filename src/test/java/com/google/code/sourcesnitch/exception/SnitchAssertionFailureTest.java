
package com.google.code.sourcesnitch.exception;

import com.google.common.collect.Lists;
import java.util.Collections;
import com.google.common.collect.Sets;
import org.junit.Before;
import com.google.code.sourcesnitch.io.SourceFile;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 * @author Nick Faulkner
 */
@RunWith(MockitoJUnitRunner.class)
public class SnitchAssertionFailureTest {
    @Mock SourceFile mockSourceFileOne;
    @Mock SourceFile mockSourceFileTwo;

    @Before
    public void setUp() {
        when(mockSourceFileOne.getFileName()).thenReturn("fileone.txt");
        when(mockSourceFileTwo.getFileName()).thenReturn("filetwo.txt");
    }

    @Test(expected=NullPointerException.class)
    public void testCtorThrowsIfPassedNullSourceFileSet() {
        new SnitchAssertionFailure(null, "test");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCtorThrowsIfPassedEmptySourceFileSet() {
        new SnitchAssertionFailure(Collections.EMPTY_SET, "test");
    }

    @Test(expected=NullPointerException.class)
    public void testCtorThrowsIfPassedNullDescription() {
        new SnitchAssertionFailure(Sets.newHashSet(mockSourceFileOne), null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCtorThrowsIfPassedEmptyDescription() {
        new SnitchAssertionFailure(Sets.newHashSet(mockSourceFileOne), "");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCtorThrowsIfPassedBlankDescription() {
        new SnitchAssertionFailure(Sets.newHashSet(mockSourceFileOne), "     ");
    }

    @Test
    public void testCtorGeneratesExpectedMessageSingle() {
        SnitchAssertionFailure exception = new SnitchAssertionFailure(Sets.newHashSet(mockSourceFileOne),
                                                                      "didn't contain the expected text");
        assertEquals("Files found that didn't contain the expected text: fileone.txt",
                     exception.getMessage());
    }

    @Test
    public void testCtorGeneratesExpectedMessageMultiple() {
        SnitchAssertionFailure exception = new SnitchAssertionFailure(Sets.newLinkedHashSet(Lists.newArrayList(mockSourceFileOne, mockSourceFileTwo)),
                                                                      "didn't contain the expected text");
        assertEquals("Files found that didn't contain the expected text: fileone.txt, filetwo.txt",
                     exception.getMessage());
    }

}