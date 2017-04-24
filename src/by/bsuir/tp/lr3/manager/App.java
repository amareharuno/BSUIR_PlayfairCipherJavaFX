package by.bsuir.tp.lr3.manager;

import by.bsuir.tp.lr3.constantString.Message;
import by.bsuir.tp.lr3.playfairCipher.PlayfairCipher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        System.out.println(getClass().getResource("/resources/PlayfairCipher.fxml").getPath());
        fxmlLoader.setLocation(getClass().getResource("/resources/PlayfairCipher.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Title");
        primaryStage.show();
    }

    public static void openMenu() {
        System.out.println(Message.APP_DESCRIPTION);
        System.out.println(Message.DELIMITER);

        boolean isExit = false;

        while (!isExit) {
            System.out.println(Message.MENU_ITEMS);
            switch (InputVerification.inputNonNegativeNumber()) {
                case 1:
                    System.out.println(Message.DELIMITER);
                    PlayfairCipher.encrypt();
                    System.out.println(Message.DELIMITER);
                    break;
                case 2:
                    System.out.println(Message.DELIMITER);
                    PlayfairCipher.decrypt();
                    System.out.println(Message.DELIMITER);
                    break;
                case 0:
                    System.out.println(Message.DELIMITER);
                    System.out.println(Message.CLOSE_APP);
                    isExit = true;
                    break;
            }
        }
    }
}
