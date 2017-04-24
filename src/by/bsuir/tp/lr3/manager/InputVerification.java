package by.bsuir.tp.lr3.manager;

import by.bsuir.tp.lr3.constantString.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class InputVerification {
    static int inputNonNegativeNumber() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int inputNumber = -1;

        while (inputNumber < 0) {
            try {
                inputNumber = Integer.parseInt(reader.readLine());

                if (inputNumber >= 0) {
                    return inputNumber;
                }
                else {
                    System.out.println(Message.VALUE_MUST_BE_POSITIVE);
                }
            } catch (NumberFormatException | InputMismatchException ex) {
                System.out.println(Message.WRONG_INPUT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return inputNumber;
    }

    public static String checkStringWithoutSpaces(String stringForCheck) {
        String checkedString;
        checkedString = stringForCheck.replace(" ", "").toUpperCase();
        checkedString = checkedString.replace("\n", "");
        checkedString = checkedString.replace("\t", "");

        if (checkedString.equals("")) {
            System.out.println(Message.WRONG_STRING_INPUT);
        }
        return checkedString;
    }
}
