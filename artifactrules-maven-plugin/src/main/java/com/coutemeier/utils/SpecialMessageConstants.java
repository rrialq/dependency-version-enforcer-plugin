package com.coutemeier.utils;

/**
 * Definition of message constants used at this library.
 */
public final class SpecialMessageConstants {

    /**
     * Represents the new line characters, to simplify expressions.
     */
    public static final String NEW_LINE =  System.getProperty("line.separator");

    /**
     * Represents the tab character, to simplify expressions.
     */
    public static final String TAB = "    ";

    /**
     * Level 1 of the headers for some outputs.
     * A new line followed by one tabulator.
     */
    public static final String HEADER_1 = NEW_LINE + TAB;

    /**
     * Level 2 of the headers for some outputs.
     * A new line followed by two tabulators.
     */
    public static final String HEADER_2 = NEW_LINE + TAB + TAB;

    /**
     * Level 3 of the headers for some outputs.
     * A new line followed by three tabulators.
     */
    public static final String HEADER_3 = NEW_LINE + TAB + TAB + TAB;

    private SpecialMessageConstants() {
        super();
    }
}