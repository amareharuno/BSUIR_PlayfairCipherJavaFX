package by.bsuir.tp.lr3.playfairCipher;

import by.bsuir.tp.lr3.constantString.Message;
import by.bsuir.tp.lr3.manager.Helper;
import by.bsuir.tp.lr3.manager.InputVerification;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class PlayfairCipher {
    @FXML
    private TextField key;
    @FXML
    private TextArea text;
    @FXML
    private TextArea console;

    public void encrypt() {
        String key = InputVerification.checkStringWithoutSpaces(this.key.getText());
        String sourceText = InputVerification.checkStringWithoutSpaces(this.text.getText());

        if (key.equals("") || sourceText.equals("")) {
            console.setText(Message.EMPTY_STRING);
            return;
        }

        char[][] matrix = formMatrix(key);
        LinkedList<Character> bigrams = divideToBigrams(sourceText);

        StringBuilder text = new StringBuilder();
        text.append(Message.ENCRYPTION)
                .append(Message.LOCAL_DELIMITER)
                .append("The key            |   ").append(key).append("\n")
                .append("Source text      |   ").append(sourceText).append("\n")
                .append("Text with 'X'     |   ").append(Helper.LinkedListToString(bigrams)).append("\n")
                .append("Encrypted text  |   ").append(encryptBigrams(bigrams, matrix)).append("\n")
                .append(Message.LOCAL_DELIMITER)
                .append(Message.MATRIX_FOR_ENCRYPTION)
                .append(Message.LOCAL_DELIMITER)
                .append(printMatrix(matrix))
                .append(Message.LOCAL_DELIMITER);

        console.setText(text.toString());
    }

    public void decrypt() {
        StringBuilder text = new StringBuilder();
        text.append(Message.DECRYPTION).append("\n").append(Message.LOCAL_DELIMITER);

        String key = InputVerification.checkStringWithoutSpaces(this.key.getText());
        String sourceText = InputVerification.checkStringWithoutSpaces(this.text.getText());

        if (key.equals("") || sourceText.equals("")) {
            console.setText(Message.EMPTY_STRING);
        }

        char[][] matrix = formMatrix(key);

        LinkedList<Character> bigrams = divideToBigrams(sourceText);
        String decryptedTextWithX = decryptBigrams(bigrams, matrix);

        text.append("The key         | ").append(key).append("\n")
                .append("Source text     | ").append(sourceText).append("\n")
                .append("Text with 'X'   | ").append(Helper.LinkedListToString(bigrams)).append("\n")
                .append("Encrypted text  | ").append(deleteXFromDecryptedText(decryptedTextWithX)).append("\n")
                .append(Message.LOCAL_DELIMITER)
                .append(Message.MATRIX_FOR_DECRYPTION)
                .append(Message.LOCAL_DELIMITER)
                .append(printMatrix(matrix))
                .append(Message.LOCAL_DELIMITER);

        console.setText(text.toString());
    }

    public void openTextFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select text file.");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file","*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);

        StringBuilder textToConsole = new StringBuilder();
        if (selectedFile != null){
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile.getAbsoluteFile()))) {
                String string;
                while ((string = reader.readLine()) != null){
                    textToConsole.append(string).append("\n");
                }
            } catch (FileNotFoundException e) {
                console.setText("File not found.");
                return;
            } catch (IOException e) {
                console.setText("IOException.");
                return;
            }
        }
        text.setText(textToConsole.toString());
    }
    
    private static char[][] formMatrix(String key) {
        char[][] matrix = new char[5][5];
        char[] charMatrixKey = Helper.deleteRepeatedLettersFromString(key).toCharArray();
        char[] alphabet = {'A','B','C','D','E','F','G','H','I','K','L',
                'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int i = 0;
        int letterIndex = 0;

        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 5; columnIndex++) {
                if (i < charMatrixKey.length) {
                    matrix[rowIndex][columnIndex] = charMatrixKey[i];
                    i++;
                }
                else {
                    if (letterIndex < alphabet.length){
                        boolean added = false;
                        while (!added) {
                            if (!key.contains(Character.toString(alphabet[letterIndex]))) {
                                matrix[rowIndex][columnIndex] = alphabet[letterIndex];
                                added = true;
                            } else {
                                letterIndex++;
                            }
                        }
                        letterIndex++;
                    }
                }
            }
        }
        return matrix;
    }

    private String printMatrix(char[][] matrix) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 5; columnIndex++) {
                if (columnIndex == 4) {
                    stringBuilder
                            .append(matrix[rowIndex][columnIndex])
                            .append(" ")
                            .append("\n");
                }
                else {
                    stringBuilder.append(matrix[rowIndex][columnIndex]).append(" ");
                }
            }
        }

        return stringBuilder.toString();
    }
    
    private static LinkedList<Character> divideToBigrams(String encryptedText) {
        LinkedList<Character> newEncryptedText = Helper.StringToLinkedList(encryptedText);

        for (int i = 0; i < newEncryptedText.size() - 1; i+=2) {
            if (newEncryptedText.get(i).equals(newEncryptedText.get(i+1))) {
                newEncryptedText.add(i+1, 'X');
            }
        }

        if (newEncryptedText.size()%2 != 0) {
            newEncryptedText.add('X');
        }

        System.out.println();
        return newEncryptedText;
    }

    private static String encryptBigrams(LinkedList<Character> text, char[][] matrix) {
        LinkedList<Character> encryptedText = new LinkedList<>(text);
        for (int letterIndex = 0; letterIndex < text.size(); letterIndex+=2) {
            HashMap<String, Integer> indexes = findBigramsIndexesInMatrix(
                    text.get(letterIndex), text.get(letterIndex+1), matrix);

            int col1 = indexes.get(Message.COL1);
            int col2 = indexes.get(Message.COL2);
            int row1 = indexes.get(Message.ROW1);
            int row2 = indexes.get(Message.ROW2);

            if (Objects.equals(col1, col2)) { // в одном столбце
                if (Objects.equals(row1, 4)) {
                    encryptedText.set(letterIndex, matrix[0][col1]);
                }
                else {
                    encryptedText.set(letterIndex, matrix[row1+1][col1]);
                }

                if (Objects.equals(row2, 4)) {
                    encryptedText.set(letterIndex+1, matrix[0][col2]);
                }
                else {
                    encryptedText.set(letterIndex+1, matrix[row2+1][col2]);
                }
            }
            else if (Objects.equals(row1, row2)){ // в одной строке
                if (Objects.equals(col1, 4)) {
                    encryptedText.set(letterIndex, matrix[row1][0]);
                }
                else {
                    encryptedText.set(letterIndex, matrix[row1][col1+1]);
                }

                if (Objects.equals(col2, 4)) {
                    encryptedText.set(letterIndex+1, matrix[row2][0]);
                }
                else {
                    encryptedText.set(letterIndex+1, matrix[row2][col2+1]);
                }
            }
            else { // "прямоугольником"
                encryptedText.set(letterIndex, matrix[row1][col2]);
                encryptedText.set(letterIndex+1,matrix[row2][col1]);
            }
        }

        return Helper.LinkedListToString(encryptedText);
    }

    private static String decryptBigrams(LinkedList<Character> text, char[][] matrix) {
        LinkedList<Character> decryptedText = new LinkedList<>(text);
        for (int letterIndex = 0; letterIndex < text.size(); letterIndex+=2) {
            HashMap<String, Integer> indexes = findBigramsIndexesInMatrix(
                    text.get(letterIndex), text.get(letterIndex+1), matrix);

            int col1 = indexes.get(Message.COL1);
            int col2 = indexes.get(Message.COL2);
            int row1 = indexes.get(Message.ROW1);
            int row2 = indexes.get(Message.ROW2);

            if (Objects.equals(col1, col2)) { // в одном столбце
                if (Objects.equals(row1, 0)) {
                    decryptedText.set(letterIndex, matrix[4][col1]);
                }
                else {
                    decryptedText.set(letterIndex, matrix[row1-1][col1]);
                }

                if (Objects.equals(row2, 0)) {
                    decryptedText.set(letterIndex+1, matrix[4][col2]);
                }
                else {
                    decryptedText.set(letterIndex+1, matrix[row2-1][col2]);
                }
            }
            else if (Objects.equals(row1, row2)){ // в одной строке
                if (Objects.equals(col1, 0)) {
                    decryptedText.set(letterIndex, matrix[row1][4]);
                }
                else {
                    decryptedText.set(letterIndex, matrix[row1][col1-1]);
                }

                if (Objects.equals(col2, 0)) {
                    decryptedText.set(letterIndex+1, matrix[row2][4]);
                }
                else {
                    decryptedText.set(letterIndex+1, matrix[row2][col2-1]);
                }
            }
            else { // "прямоугольником"
                decryptedText.set(letterIndex, matrix[row1][col2]);
                decryptedText.set(letterIndex+1,matrix[row2][col1]);
            }
        }

        return Helper.LinkedListToString(decryptedText);
    }

    private static HashMap<String, Integer> findBigramsIndexesInMatrix(char letter1, char letter2, char[][] matrix){
        HashMap<String, Integer> indexes = new HashMap<>();

        // поиск индекса исходных букв
        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            for (int colIndex = 0; colIndex < 5; colIndex++) {
                if (letter1 == 'J' && matrix[rowIndex][colIndex] == 'I') {
                    indexes.put(Message.COL1, colIndex);
                    indexes.put(Message.ROW1, rowIndex);
                }
                if (letter2 == 'J' && matrix[rowIndex][colIndex] == 'I') {
                    indexes.put(Message.COL2, colIndex);
                    indexes.put(Message.ROW2, rowIndex);
                }
                if (letter1 == matrix[rowIndex][colIndex]) {
                    indexes.put(Message.COL1, colIndex);
                    indexes.put(Message.ROW1, rowIndex);
                }
                if (letter2 == matrix[rowIndex][colIndex]){
                    indexes.put(Message.COL2, colIndex);
                    indexes.put(Message.ROW2, rowIndex);
                }
            }
        }
        return indexes;
    }

    private static String deleteXFromDecryptedText(String decryptedText) {
        LinkedList<Character> text = Helper.StringToLinkedList(decryptedText);
        StringBuilder textWithoutX = new StringBuilder();

        for (Character item : text) {
            if (!item.equals('X')) {
                textWithoutX.append(item);
            }
        }
        return textWithoutX.toString();
    }
}
