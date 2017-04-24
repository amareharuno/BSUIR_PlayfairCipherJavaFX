package by.bsuir.tp.lr3.manager;

import by.bsuir.tp.lr3.constant.ConstantString;

public class InputVerification {
    public static String checkStringWithoutSpaces(String stringForCheck) {
        String checkedString;
        checkedString = stringForCheck.replace(" ", "").toUpperCase();
        checkedString = checkedString.replace("\n", "");
        checkedString = checkedString.replace("\t", "");

        if (checkedString.equals("") || !checkedString.matches(ConstantString.REGEX_ONLY_LATIN_LETTERS)) {
            System.out.println(ConstantString.WRONG_STRING_INPUT);
            return "";
        }
        return checkedString;
    }
}
