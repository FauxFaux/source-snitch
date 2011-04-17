
package com.google.code.sourcesnitch;

import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Helper test methods (obscure name to hide it from JUnit)
 * @author Nick Faulkner
 */
public class TeztHelper {
    public static String contentsOfTestFile(String relativeFilePath) throws IOException {
        InputStream in = TeztHelper.class.getResourceAsStream("/test-data/" + relativeFilePath);
        String contents = CharStreams.toString(new InputStreamReader(in));
        Closeables.closeQuietly(in);
        return contents;
    }
}