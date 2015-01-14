package com.coutemeier.utils;

import java.io.Closeable;
import java.io.InputStream;
import java.io.IOException;

public enum IOUtils {
    INSTANCE;

    public static void closeSilently(final Closeable ... closeables) {
        for(Closeable closeable: closeables) {
            try {
                closeable.close();
            } catch (IOException cause) {

            }
        }
    }
}