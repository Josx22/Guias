module com.example.formularioingreso {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.formularioingreso to javafx.fxml;
    exports com.example.formularioingreso;
}