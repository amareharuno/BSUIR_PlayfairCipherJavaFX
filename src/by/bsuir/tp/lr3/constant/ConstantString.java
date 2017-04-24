package by.bsuir.tp.lr3.constant;

public final class ConstantString {
    // regex
    public static final String REGEX_ONLY_LATIN_LETTERS = "[a-zA-Z]+";

    // InputVerification
    public static final String WRONG_STRING_INPUT = "Wrong input: the string is empty or contains not only latin letters.";
    public static final String WRONG_INPUT = "Wrong input: " +
            "\nEnter right Key and Text values: " +
            "\n - Strings shouldn't be empty. " +
            "\n - Use only latin letters.";

    // HashMap keys
    public static final String COL1 = "letter1ColIndex";
    public static final String COL2 = "letter2ColIndex";
    public static final String ROW1 = "letter1RowIndex";
    public static final String ROW2 = "letter2RowIndex";

    // files
    public static final String SELECT_TEXT_FILE = "Select text file.";
    public static final String FILE_NOT_FOUND = "File not found.";
    public static final String IO_EXCEPTION = "IOException.";

    public static final String NO_TEXT_TO_SAVE = "There is no text to save.";
    public static final String SAVE_TEXT_FILE = "Save text file.";
    public static final String INITIAL_FILE_NAME = "MyText.txt";
    public static final String SOMETHING_WRONG_IN_FILE_WRIGHT = "Something goes wrong. File wasn't save. Try to save it again.";

    // extensions
    public static final String TEXT_FILE = "Text file";
    public static final String TXT = "*.txt";
}
