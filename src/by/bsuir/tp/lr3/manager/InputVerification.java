package by.bsuir.tp.lr3.manager;

import by.bsuir.tp.lr3.constantString.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class InputVerification {
    // Возвращает правильное число и проверяет на правильность (inputNonNegativeNumber >= 0)
    public static int inputNonNegativeNumber() {
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

    // Возвращает правильную строку (не пустая строка верхнего регистра без пробелов)
    // preInputMessage - строка-сообщение, обозначающая, что надо ввести
    public static String inputStringWithoutSpaces(String preInputMessage) {
        String string = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println(preInputMessage);
                string = reader.readLine().replace(" ", "").toUpperCase();
                if (string.equals("")) {
                    System.out.println(Message.WRONG_STRING_INPUT);
                }
                else {
                    return string;
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.toString());
            exception.printStackTrace();
        }
        return string;
    }
}
