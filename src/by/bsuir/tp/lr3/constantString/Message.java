package by.bsuir.tp.lr3.constantString;

public final class Message {

    // Common
    public static final String DELIMITER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    public static final String LOCAL_DELIMITER = "_____________________________\n";

    // App
    public static final String APP_DESCRIPTION = "This app can encrypt (or decrypt) you message with Playfair cipher.\n" +
            "Please, use only latin letters.";

    // InputVerification
    public static final String VALUE_MUST_BE_POSITIVE = "Wrong input: number must be >= 0.";
    public static final String WRONG_INPUT = "Wrong input: enter a number which is >= 0.";
    public static final String WRONG_STRING_INPUT = "Wrong input: the string is empty. Please repeat.";

    // Encryption & Decryption
    public static final String ENCRYPTION = "ENCRYPTION\n";
    public static final String MATRIX_FOR_ENCRYPTION = "Matrix for encryption:\n";

    public static final String DECRYPTION = "DECRYPTION\n";
    public static final String MATRIX_FOR_DECRYPTION = "Matrix for decryption:\n";

    // HashMap keys
    public static final String COL1 = "letter1ColIndex";
    public static final String COL2 = "letter2ColIndex";
    public static final String ROW1 = "letter1RowIndex";
    public static final String ROW2 = "letter2RowIndex";
    public static final String EMPTY_STRING = "Key or Text for encryption|decryption is empty. Enter needed data.\n";
}
