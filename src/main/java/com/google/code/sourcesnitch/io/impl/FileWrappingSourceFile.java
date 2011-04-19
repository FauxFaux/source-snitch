
package com.google.code.sourcesnitch.io.impl;

import com.google.common.base.Objects;
import com.google.code.sourcesnitch.exception.SnitchFileReadException;
import com.google.code.sourcesnitch.io.SourceFile;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static com.google.common.base.Preconditions.*;

/**
 *
 * @author Nick Faulkner
 */
public class FileWrappingSourceFile implements SourceFile {
    private final File wrappedFile;

    /**
     * 
     * @param wrappedFile file to wrap
     */
    public FileWrappingSourceFile(File wrappedFile) throws FileNotFoundException, IOException {
        this.wrappedFile = checkNotNull(wrappedFile, "File must be specified");

        if (wrappedFile.isDirectory()) {
            throw new IllegalArgumentException("Can not create source file from directory: " + this.wrappedFile);
        }
        if (!wrappedFile.exists()) {
            throw new FileNotFoundException("File not found at: " + this.wrappedFile);
        }
        if (!wrappedFile.canRead()) {
            throw new FileNotFoundException("Can not read file (but it exists): " + this.wrappedFile);
        }
    }

    @Override
    public String readFully() throws SnitchFileReadException {
        InputStream in = null;
        try {
            in = new FileInputStream(wrappedFile);
            return CharStreams.toString(new InputStreamReader(in));
        } catch (IOException ioe) {
            throw new SnitchFileReadException(ioe);
        } finally {
            Closeables.closeQuietly(in);
        }
    }

    @Override
    public String getFileName() {
        return wrappedFile.getName();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(this.getFileName()).toString();
    }
}
