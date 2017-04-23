package by.bsuir.tp.lr3.constantString;

public final class Message {

    // Common
    public static final String DELIMITER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    public static final String LOCAL_DELIMITER = "_____________________________";

    // Menu
    public static final String APP_DESCRIPTION = "This app can encrypt (or decrypt) you message with Playfair cipher.\n" +
            "Please, use only latin letters.";
    public static final String MENU_ITEMS = "Choose what you want to do:\n" +
            "1. encrypt\n" +
            "2. decrypt\n" +
            "0. close app";
    public static final String CLOSE_APP = "The app is closed. Have a nice day :)";

    // InputVerification
    public static final String VALUE_MUST_BE_POSITIVE = "Wrong input: number must be >= 0.";
    public static final String WRONG_INPUT = "Wrong input: enter a number which is >= 0.";
    public static final String WRONG_STRING_INPUT = "Wrong input: the string is empty. Please repeat.";

    // Encryption & Decryption
    public static final String ENTER_KEY = "Enter the key:";

    public static final String ENCRYPTION = "___ ENCRYPTION ___";
    public static final String ENTER_TEXT_TO_ENCRYPT = "Enter text to encrypt:";
    public static final String MATRIX_FOR_ENCRYPTION = "___ Matrix for encryption ___\n";

    public static final String DECRYPTION = "___ DECRYPTION ___";
    public static final String ENTER_TEXT_TO_DECRYPT = "Enter text to decrypt:";
    public static final String MATRIX_FOR_DECRYPTION = "___ Matrix for decryption ___\n";

    // HashMap keys
    public static final String COL1 = "letter1ColIndex";
    public static final String COL2 = "letter2ColIndex";
    public static final String ROW1 = "letter1RowIndex";
    public static final String ROW2 = "letter2RowIndex";
}
