
package com.google.code.sourcesnitch.snitches;

import com.google.code.sourcesnitch.exception.SnitchAssertionFailure;

/**
 *
 * @author Nick Faulkner
 */
public interface JavaSnitchAssertions extends GenericSnitchAssertions {
    /**
     * Throws if any java file writes to System.out or System.err.
     */
    void doNotWriteToSytemOutOrErr() throws SnitchAssertionFailure;
    
    /**
     * Throws if any java file does not import the passed package.
     * E.g. "org.omg.CORBA"
     */
    void importPackage(String packageName) throws SnitchAssertionFailure;

    /**
     * Throws if any java file imports the passed package.
     * E.g. "org.omg.CORBA"
     */
    void doNotImportPackage(String packageName) throws SnitchAssertionFailure;    
}
