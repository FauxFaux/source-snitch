
package com.google.code.sourcesnitch.io.impl;

import com.google.code.sourcesnitch.exception.SnitchFileReadException;
import com.google.code.sourcesnitch.io.Filesystem;
import com.google.code.sourcesnitch.io.SourceFile;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Set;

/**
 * Expects a maven project and uses the 'src/main' directory as the root of the
 * filesystem.
 *
 * @author Nick Faulkner
 */
public class MavenFilesystem implements Filesystem {
    private final File root;
   
    /**
     * 
     */
    public MavenFilesystem() {
        File projectRoot = new File(MavenFilesystem.class.getResource("/").getFile()).getParentFile().getParentFile();
        this.root = new File(new File(projectRoot, "src"), "main");
    }

    public Set<SourceFile> getAllFilesWithExtension(final String extension) {
        Preconditions.checkNotNull(extension, "Extension must not be null");
        Preconditions.checkArgument(!extension.trim().isEmpty(), "Extension must not be blank or empty");

        Set<File> files = this.recursivelyListFilesWithExtension(root, new FileFilter() {
            @Override public boolean accept(File file) {
                return file.isDirectory() || file.getName().endsWith(extension);
            }
        });

        return Sets.newHashSet(Collections2.transform(files, new Function<File, SourceFile>() {
            @Override public SourceFile apply(File file) {
                try {
                    return new FileWrappingSourceFile(file);
                } catch (IOException ioe) {
                    throw new SnitchFileReadException(ioe);
                }
            }
        }));
    }

    @VisibleForTesting
    File getRoot() {
        return this.root;
    }

    private Set<File> recursivelyListFilesWithExtension(File dir, FileFilter filter) {
        Set<File> files = Sets.newHashSet();
        for (File child : dir.listFiles(filter)) {
            if (child.isDirectory()) {
                files.addAll(recursivelyListFilesWithExtension(child, filter));
            } else {
                files.add(child);
            }
        }
        return files;
    }
}
