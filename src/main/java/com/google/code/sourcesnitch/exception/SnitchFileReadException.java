
package com.google.code.sourcesnitch.exception;

import java.io.IOException;

/**
 * Basically just here to wrap IOExceptions in runtimes.
 * @author Nick Faulkner
 */
public class SnitchFileReadException extends RuntimeException {

    public SnitchFileReadException(IOException ioe) {
        super(ioe);
    }

}
