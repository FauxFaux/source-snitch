
package com.google.code.sourcesnitch;

import com.google.code.sourcesnitch.io.Filesystem;
import com.google.code.sourcesnitch.io.FilesystemFactory;
import com.google.code.sourcesnitch.io.SourceFile;
import com.google.code.sourcesnitch.snitches.GenericSnitchAssertions;
import com.google.code.sourcesnitch.snitches.JavaSnitchAssertions;
import com.google.code.sourcesnitch.snitches.impl.GenericSnitchImpl;
import com.google.code.sourcesnitch.snitches.impl.JavaSnitchImpl;
import java.util.Set;

/**
 *
 * @author Nick Faulkner
 */
public class Snitch {
    /**
     * Returns generic assertions. For more domain specific assertions, consider
     * using alternative methods of this class.
     */
    public static GenericSnitchAssertions assertAllFilesOfType(String filetype) {
        return new GenericSnitchImpl(loadFilesOfType(filetype));
    }

    /**
     * Returns generic and Java specific assertions.
     */
    public static JavaSnitchAssertions assertAllJavaFiles() {
        return new JavaSnitchImpl(loadFilesOfType("java"));
    }

    private static Set<SourceFile> loadFilesOfType(String filetype) {
        Filesystem filesystem = FilesystemFactory.getDefaultFilesystem();
        return filesystem.getAllFilesWithExtension(filetype);
    }

    // TODO remove and add to wiki
    public static void main(String[] args) {
        Snitch.assertAllFilesOfType("java").contain("test");
        Snitch.assertAllFilesOfType("java").match(".*test");
        Snitch.assertAllFilesOfType("java").doNotContain("test");
        Snitch.assertAllFilesOfType("java").doNotMatch(".*test");
        Snitch.assertAllJavaFiles().importPackage("uk.co.test");
        Snitch.assertAllJavaFiles().doNotImportPackage("uk.co.test");
    }
}
