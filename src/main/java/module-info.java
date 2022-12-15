module com.example.cosas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cosas to javafx.fxml;
    exports com.example.cosas;
}