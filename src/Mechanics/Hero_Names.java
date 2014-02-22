package Mechanics;


public class Hero_Names {
    public static final String OPTIMIST = "Kirthas";
    public static final String RATIONAL = "Isabelle";
    public static final String APATHETIC = "Damian";
    public static final String CYNIC = "Sky";

    public static final String OPTIMIST_TITLE = "Optimist";
    public static final String RATIONAL_TITLE = "Rational";
    public static final String APATHETIC_TITLE = "Apathetic";
    public static final String CYNIC_TITLE = "Cynic";

    public static final int OPTIMIST_ID = 0;
    public static final int RATIONAL_ID = 1;
    public static final int APATHETIC_ID = 2;
    public static final int CYNIC_ID = 3;

    public static String getName(int id) {
        switch (id) {
        case OPTIMIST_ID:
            return OPTIMIST;
        case RATIONAL_ID:
            return RATIONAL;
        case APATHETIC_ID:
            return APATHETIC;
        case CYNIC_ID:
            return CYNIC;
        }
        throw new IllegalArgumentException("ID must be between 0 and 3");
    }

    public static String getTitle(int id) {
        switch (id) {
        case OPTIMIST_ID:
            return OPTIMIST_TITLE;
        case RATIONAL_ID:
            return RATIONAL_TITLE;
        case APATHETIC_ID:
            return APATHETIC_TITLE;
        case CYNIC_ID:
            return CYNIC_TITLE;
        }
        throw new IllegalArgumentException("ID must be between 0 and 3");
    }

}
