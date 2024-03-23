module org.example.clientexemplerestipa {
    requires static lombok;

    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires com.google.gson;

    opens org.example.clientrestipa to javafx.fxml;

    exports org.example.clientrestipa;
    exports org.example.clientrestipa.controllers;
    exports org.example.clientrestipa.controllers.tables;
    exports org.example.clientrestipa.utils;
    exports org.example.clientrestipa.dto;


    opens org.example.clientrestipa.dto to com.google.gson;
    opens org.example.clientrestipa.controllers to javafx.fxml;
    opens org.example.clientrestipa.controllers.tables to javafx.fxml;
}