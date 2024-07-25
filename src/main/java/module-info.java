module com.uet.dictionary_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jfr;
    requires static lombok;

    opens com.uet.dictionary_java to javafx.fxml;
    exports com.uet.dictionary_java;
}