
package com.google.code.sourcesnitch.io;

import com.google.code.sourcesnitch.io.impl.MavenFilesystem;

/**
 *
 * @author Nick Faulkner
 */
public class FilesystemFactory {
    public static Filesystem getDefaultFilesystem() {
        return new MavenFilesystem();
    }
}
