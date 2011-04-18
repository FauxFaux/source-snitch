
package com.google.code.sourcesnitch.util;

import com.google.code.sourcesnitch.io.SourceFile;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.util.regex.Pattern;

/**
 * @author Nick Faulkner
 */
public class SnitchPredicates {

    public static Predicate<SourceFile> contains(final String string) {
        Preconditions.checkNotNull(string);
        Preconditions.checkArgument(!string.isEmpty());
        
        return new Predicate<SourceFile>() {
            @Override public boolean apply(SourceFile file) {
                return file.readFully().contains(string);
            }
        };
    }

    public static Predicate<SourceFile> matches(final String patternString) {
        Preconditions.checkNotNull(patternString);
        Preconditions.checkArgument(!patternString.isEmpty());

        final Pattern pattern = Pattern.compile(patternString, Pattern.DOTALL);
        
        return new Predicate<SourceFile>() {
            @Override public boolean apply(SourceFile file) {
                return pattern.matcher(file.readFully()).matches();
            }
        };
    }
}
