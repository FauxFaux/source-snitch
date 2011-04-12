
package com.google.code.sourcesnitch.snitches;

import com.google.code.sourcesnitch.exception.SnitchAssertionFailure;

/**
 *
 * @author Nick Faulkner
 */
public interface GenericSnitchAssertions {
    /**
     * Throws if any file does not contain the passed string.
     */
    void contain(String string) throws SnitchAssertionFailure;

    /**
     * Throws if any file contains the passed string.
     */
    void doNotContain(String string) throws SnitchAssertionFailure;
    
    /**
     * Throws if any file does not match passed pattern.
     */
    void match(String pattern) throws SnitchAssertionFailure;

    /**
     * Throws if any file matches the passed pattern.
     */
    void doNotMatch(String pattern) throws SnitchAssertionFailure;
}
