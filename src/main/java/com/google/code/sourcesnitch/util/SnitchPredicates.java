
package com.google.code.sourcesnitch.util;

import com.google.code.sourcesnitch.io.SourceFile;
import com.google.common.base.Predicate;

/**
 * TODO test and report
 * @author Nick Faulkner
 */
public class SnitchPredicates {

    public static Predicate<SourceFile> contains(final String string) {
        return new Predicate<SourceFile>() {
            @Override public boolean apply(SourceFile file) {
                return file.readFully().contains(string);
            }
        };
    }

    public static Predicate<SourceFile> matches(final String pattern) {
        return new Predicate<SourceFile>() {
            @Override public boolean apply(SourceFile file) {
                return file.readFully().matches(pattern);
            }
        };
    }
}
