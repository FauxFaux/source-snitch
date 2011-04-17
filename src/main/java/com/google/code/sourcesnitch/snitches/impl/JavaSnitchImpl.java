
package com.google.code.sourcesnitch.snitches.impl;

import com.google.common.base.Predicate;
import com.google.code.sourcesnitch.exception.SnitchAssertionFailure;
import com.google.code.sourcesnitch.io.SourceFile;
import com.google.code.sourcesnitch.snitches.JavaSnitchAssertions;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.Set;
import static com.google.common.base.Predicates.*;
import static com.google.code.sourcesnitch.util.SnitchPredicates.*;

/**
 * 
 * @author Nick Faulkner
 */
public class JavaSnitchImpl extends GenericSnitchImpl implements JavaSnitchAssertions {
    private final Set<SourceFile> files;

    public JavaSnitchImpl(Set<SourceFile> files) {
        super(files);
        this.files = Sets.newLinkedHashSet(files);
    }

    public void importPackage(String packageName) throws SnitchAssertionFailure {
        Preconditions.checkNotNull("Package to check must be specified");
        Set<SourceFile> violations = filterFiles(not(contains("import " + packageName)));
        throwIfViolations(violations, "do not import package '" + packageName + "'");
    }

    public void doNotImportPackage(String packageName) throws SnitchAssertionFailure {
        Preconditions.checkNotNull("Package to check must be specified");
        Set<SourceFile> violations = filterFiles(contains(("import " + packageName)));
        throwIfViolations(violations, "import package '" + packageName + "'");
    }

    public void doNotWriteToSytemOutOrErr() throws SnitchAssertionFailure {
        Preconditions.checkNotNull("Package to check must be specified");
        Predicate<SourceFile> filter = or(contains("System.out.print"),
                                          contains("System.err.print"));
        Set<SourceFile> violations = filterFiles(filter);
        throwIfViolations(violations, "print to system");
    }
}
