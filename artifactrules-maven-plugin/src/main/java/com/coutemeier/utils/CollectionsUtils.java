package com.coutemeier.utils;

import java.util.Collection;

public enum CollectionsUtils {
    INSTANCE;

    public static final String asString(Collection<? extends String> collection) {
        if (isEmpty(collection)) {
            return "";
        }
        final StringBuilder asList = new StringBuilder(64);
        for(String item: collection) {
            asList.append(", ").append(item);
        }
        return asList.substring(2);
    }

    public static final boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.size() == 0);
    }
}