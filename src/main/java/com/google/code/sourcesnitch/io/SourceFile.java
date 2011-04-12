
package com.google.code.sourcesnitch.io;

import com.google.code.sourcesnitch.exception.SnitchFileReadException;

/**
 *
 * @author Nick Faulkner
 */
public interface SourceFile {
    String getFileName();
    String readFully() throws SnitchFileReadException;
}
