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
    exports org.example.clientrestipa.controllers.tables.connection;
    exports org.example.clientrestipa.controllers.tables.simple;
    exports org.example.clientrestipa.utils;
    exports org.example.clientrestipa.dto;
    exports org.example.clientrestipa.servise;
    exports org.example.clientrestipa.servise.connections;
    exports org.example.clientrestipa.managers;


    opens org.example.clientrestipa.dto to com.google.gson;
    opens org.example.clientrestipa.managers to com.google.gson;
    opens org.example.clientrestipa.controllers to javafx.fxml;
    opens org.example.clientrestipa.controllers.tables to javafx.fxml;
    opens org.example.clientrestipa.controllers.tables.connection to javafx.fxml;
    opens org.example.clientrestipa.controllers.tables.simple to javafx.fxml;
    exports org.example.clientrestipa.dto.connections;
    opens org.example.clientrestipa.dto.connections to com.google.gson, javafx.fxml;


}