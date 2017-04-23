package by.bsuir.tp.lr3.manager;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class Helper {
    public static String deleteRepeatedLettersFromString(String key) {
        char[] charKey = key.toCharArray();

        for (int i = 0; i < charKey.length - 1; i++) {
            for (int j = i+1; j < charKey.length; j++) {
                if (charKey[i] == charKey[j]) {
                    charKey[j] = '0';
                }
            }
        }

        StringBuilder newKey = new StringBuilder();
        for (char character : charKey) {
            if (character != '0') {
                newKey.append(character);
            }
        }
        // System.out.println("Новый ключ" + newKey.toString()); //проверка

        return newKey.toString();
    }

    public static String LinkedListToString(List<Character> list) {
        StringBuilder stringEncryptedText = new StringBuilder();
        for (Character item : list) {
            stringEncryptedText.append(item);
        }
        return stringEncryptedText.toString();
    }

    public static LinkedList<Character> StringToLinkedList (String string) {
        char[] charText = string.toCharArray();
        LinkedList<Character> linkedList = new LinkedList<>();
        for (char letter : charText) {
            linkedList.add(letter);
        }
        return linkedList;
    }
}
