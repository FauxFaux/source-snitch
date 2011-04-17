
package com.google.code.sourcesnitch.snitches.impl;

import com.google.code.sourcesnitch.exception.SnitchAssertionFailure;
import com.google.code.sourcesnitch.io.SourceFile;
import com.google.code.sourcesnitch.snitches.GenericSnitchAssertions;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import java.util.Set;
import static com.google.common.base.Predicates.*;
import static com.google.code.sourcesnitch.util.SnitchPredicates.*;

/**
 * @author Nick Faulkner
 */
public class GenericSnitchImpl implements GenericSnitchAssertions {
    private final Set<SourceFile> files;

    public GenericSnitchImpl(Set<SourceFile> files) {
        this.files = Sets.newLinkedHashSet(files);
    }

    public void contain(String string) throws SnitchAssertionFailure {
        Preconditions.checkNotNull("String to check must be specified");
        Set<SourceFile> violations = filterFiles(not(contains(string)));
        throwIfViolations(violations, "do not contain '" + string + "'");
    }

    public void doNotContain(String string) throws SnitchAssertionFailure {
        Preconditions.checkNotNull("String to check must be specified");
        Set<SourceFile> violations = filterFiles(contains(string));
        throwIfViolations(violations, "contain '" + string + "'");
    }

    public void match(String pattern) throws SnitchAssertionFailure {
        Preconditions.checkNotNull("Pattern to check must be specified");
        Set<SourceFile> violations = filterFiles(not(matches(pattern)));
        throwIfViolations(violations, "do not match '" + pattern + "'");
    }

    public void doNotMatch(String pattern) throws SnitchAssertionFailure {
        Preconditions.checkNotNull("Pattern to check must be specified");
        Set<SourceFile> violations = filterFiles(matches(pattern));
        throwIfViolations(violations, "match '" + pattern + "'");
    }

    protected Set<SourceFile> filterFiles(Predicate<SourceFile> predicate) {
        return Sets.filter(this.files, predicate);
    }

    protected static void throwIfViolations(Set<SourceFile> violations, String descriptionFragment) {
        if (!violations.isEmpty()) {
            throw new SnitchAssertionFailure(violations, descriptionFragment);
        }
    }
}
