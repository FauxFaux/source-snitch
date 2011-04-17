
package com.google.code.sourcesnitch.exception;

import com.google.code.sourcesnitch.io.SourceFile;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import java.util.Set;

/**
 * Represents a violation of a rule.
 *
 * @author Nick Faulkner
 */
public class SnitchAssertionFailure extends RuntimeException {
    /**
     *
     * @param files set of files that violated the rule.
     * @param descriptionFragment e.g. 'did not contain a copyright description'.
     */
    public SnitchAssertionFailure(Set<SourceFile> files, String descriptionFragment) {
        super(buildMessage(files, descriptionFragment));
    }

    private static String buildMessage(Set<SourceFile> files, String descriptionFragment) {
        Preconditions.checkNotNull(files, "Files must be specifed");
        Preconditions.checkArgument(!files.isEmpty(), "Files set must not be empty");

        Preconditions.checkNotNull(descriptionFragment, "Description fragment must be specifed");
        Preconditions.checkArgument(!descriptionFragment.trim().isEmpty(), "Description fragment must not be empty or blank");

        String filesString = Joiner.on(", ").join(Collections2.transform(files, new Function<SourceFile, String>(){
            @Override public String apply(SourceFile input) {
                return input.getFileName();
            }
        }));
        return "Files found that "  + descriptionFragment + ": " + filesString;
    }
}
