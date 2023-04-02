module com.fold21.project2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.fold21.project2 to javafx.fxml;
    exports com.fold21.project2;
}