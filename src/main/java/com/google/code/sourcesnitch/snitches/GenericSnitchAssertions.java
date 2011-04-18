
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
     *
     * Matches are made using single-line mode
     * (I.E. the period character matches line terminators).
     */
    void match(String pattern) throws SnitchAssertionFailure;

    /**
     * Throws if any file matches the passed pattern.
     * 
     * Matches are made using single-line mode
     * (I.E. the period character matches line terminators).
     */
    void doNotMatch(String pattern) throws SnitchAssertionFailure;
}
