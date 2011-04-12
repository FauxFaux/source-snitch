
package com.google.code.sourcesnitch.io.impl;

import com.google.code.sourcesnitch.io.SourceFile;
import java.util.Arrays;
import java.io.File;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nick Faulkner
 */
public class MavenFilesystemTest {

    @Test
    public void testRootIsCorrectlyDetected() {
        File root = new MavenFilesystem().getRoot();
        assertEquals("main", root.getName());
        assertTrue(Arrays.asList(root.getParentFile().getParentFile().list()).contains("pom.xml"));
    }

    @Test
    public void testGetAllFilesWithExtension() throws Exception {
        assertTrue(new MavenFilesystem().getAllFilesWithExtension("notfound").isEmpty());

        Set<SourceFile> javaFiles = new MavenFilesystem().getAllFilesWithExtension("java");
        assertFalse(javaFiles.isEmpty());

        for (SourceFile file : javaFiles) {
            assertFalse(file.readFully().isEmpty());
            assertTrue(file.getFileName().endsWith(".java"));
        }
    }

    @Test(expected=NullPointerException.class)
    public void testGetAllFilesWithExtensionThrowsIfPassedNullExtension() throws Exception {
        new MavenFilesystem().getAllFilesWithExtension(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetAllFilesWithExtensionThrowsIfPassedEmptyExtension() throws Exception {
        new MavenFilesystem().getAllFilesWithExtension("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetAllFilesWithExtensionThrowsIfPassedBlankExtension() throws Exception {
        new MavenFilesystem().getAllFilesWithExtension("       ");
    }
}
