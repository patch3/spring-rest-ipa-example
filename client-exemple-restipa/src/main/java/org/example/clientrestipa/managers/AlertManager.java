package org.example.clientrestipa.managers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import lombok.val;


/**
 * Менеджер с заготовками алертов javafx
 */
public class AlertManager {
    /**
     * Отображает информационный алерт с заголовком и содержимым.
     *
     * @param headerText  Заголовок алерта.
     * @param contentText Содержимое алерта.
     */
    public static void showInformationAlert(String headerText, String contentText) {
        System.out.println("Выведен информационный алерт с текстом: " + contentText);
        val alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * Отображает алерт с кнопками "OK" и "CLOSE" для подтверждения действия.
     *
     * @param contentText Содержимое алерта.
     * @return true, если нажата кнопка "OK", иначе false.
     */
    public static boolean showConfirmationAlert(String contentText) {
        System.out.println("Выведенн вопросительный аллерт с текстом: " + contentText);
        val alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("Action needs your confirmation");
        alert.setContentText(contentText);
        val presButton = alert.showAndWait();

        // какая кнопка была нажата
        if (presButton.isPresent() && presButton.get() == ButtonType.OK) {
            System.out.println("Нажата кнопка OK");
            return true;
        } else {
            System.out.println("Алерт был закрыт без нажатия кнопки OK");
            return false;
        }
    }

    /**
     * Отображает алерт об ошибке с заданным содержимым.
     *
     * @param contentText Содержимое алерта об ошибке.
     */
    public static void showErrorAlert(String contentText) {
        System.err.println(contentText);
        val alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Something went wrong!");
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
