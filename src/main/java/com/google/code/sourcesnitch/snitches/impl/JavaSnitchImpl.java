
package com.google.code.sourcesnitch.snitches.impl;

import com.google.code.sourcesnitch.exception.SnitchAssertionFailure;
import com.google.code.sourcesnitch.io.SourceFile;
import com.google.code.sourcesnitch.snitches.JavaSnitchAssertions;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.Set;
import static com.google.common.base.Predicates.*;
import static com.google.code.sourcesnitch.util.SnitchPredicates.*;

/**
 * // TODO test and report
 * @author Nick Faulkner
 */
public class JavaSnitchImpl extends GenericSnitchImpl implements JavaSnitchAssertions {
    private final Set<SourceFile> files;

    public JavaSnitchImpl(Set<SourceFile> files) {
        super(files);
        this.files = Sets.newHashSet(files);
    }

    public void importPackage(String packageName) throws SnitchAssertionFailure {
        Preconditions.checkNotNull("Package to check must be specified");

        Set<SourceFile> violations = filterFiles(not(matches(packagePattern(packageName))));
    }

    public void doNotImportPackage(String packageName) throws SnitchAssertionFailure {
        Preconditions.checkNotNull("Package to check must be specified");

        Set<SourceFile> violations = filterFiles(matches(packagePattern(packageName)));
    }

    private String packagePattern(String packageName) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
