module com.uet.dictionary_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jfr;
    requires static lombok;
    requires mysql.connector.java;
    requires java.sql;

    opens com.uet.dictionary_java to javafx.fxml;
    exports com.uet.dictionary_java;
    exports com.uet.dictionary_java.controllers;
    opens com.uet.dictionary_java.controllers to javafx.fxml;
}