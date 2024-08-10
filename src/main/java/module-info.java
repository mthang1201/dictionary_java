module com.uet.dictionary_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jfr;
    requires static lombok;
    requires mysql.connector.java;
    requires java.sql;
    requires freetts;

    opens com.uet.dictionary_java to javafx.fxml;
    exports com.uet.dictionary_java;
    exports com.uet.dictionary_java.controllers;
    opens com.uet.dictionary_java.controllers to javafx.fxml;
    exports com.uet.dictionary_java.services;
    opens com.uet.dictionary_java.services to javafx.fxml;
    exports com.uet.dictionary_java.repositories;
    opens com.uet.dictionary_java.repositories to javafx.fxml;
}