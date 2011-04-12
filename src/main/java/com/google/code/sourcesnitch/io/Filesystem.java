
package com.google.code.sourcesnitch.io;

import java.util.Set;

/**
 *
 * @author Nick Faulkner
 */
public interface Filesystem {
    Set<SourceFile> getAllFilesWithExtension(String extension);
}
