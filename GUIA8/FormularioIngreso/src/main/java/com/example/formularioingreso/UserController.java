package com.example.formularioingreso;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class UserController {
    @FXML private Label lblInfoUsuario;

    public void setUsuario(String nombre) {
        lblInfoUsuario.setText("Usuario activo: " + nombre);
    }

    @FXML
    private void handleLogout() throws IOException {
        // Cargar de nuevo la ventana de Login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(loader.load(), 300, 300);

        Stage loginStage = new Stage();
        loginStage.setTitle("Iniciar Sesión");
        loginStage.setScene(scene);
        loginStage.show();

        // Cerrar la ventana actual (la de usuario)
        Stage currentStage = (Stage) lblInfoUsuario.getScene().getWindow();
        currentStage.close();
    }
}