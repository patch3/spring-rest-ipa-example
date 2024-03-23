package org.example.clientrestipa.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.clientrestipa.Application;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    public Button btnClientAndBook;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnBook;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnClient.setOnAction(event -> {
            this.openNewView("Клиенты", "tables/client-view.fxml");
        });
        btnBook.setOnAction(event -> {
            this.openNewView("Книги", "tables/book-view.fxml");
        });
        btnClientAndBook.setOnAction(event -> {
            this.openNewView("Заказы клиентов", "tables/client-connection-view.fxml");
        });

    }

    private void openNewView(String title, String patchToView) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource(patchToView));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}