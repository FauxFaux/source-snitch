
package com.google.code.sourcesnitch.io.impl;

import com.google.code.sourcesnitch.io.SourceFile;
import java.io.File;
import org.junit.Before;
import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nick Faulkner
 */
public class FileWrappingSourceFileTest {

    private File baseDir;
    
    @Before
    public void setUp() {
         baseDir = new File(new File(FileWrappingSourceFileTest.class.getResource("/").getFile()), "test-data");
    }

    @Test(expected=NullPointerException.class)
    public void testCtorThrowsIfPassedNullFile() throws Exception {
        new FileWrappingSourceFile(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCtorThrowsIfPassedDirectory() throws Exception {
        new FileWrappingSourceFile(baseDir);
    }

    @Test(expected=FileNotFoundException.class)
    public void testReadFullyFileNotFoundThrows() throws Exception {
        new FileWrappingSourceFile(new File(baseDir, "not-found")).readFully();
    }

    @Test
    public void testReadFullyFileFound() throws Exception {
        SourceFile file = new FileWrappingSourceFile(new File(baseDir, "simple.file"));
        assertEquals("simple.file", file.getFileName());
        assertEquals("test data", file.readFully());
    }
}
