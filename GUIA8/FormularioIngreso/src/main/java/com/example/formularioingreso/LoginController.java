package com.example.formularioingreso;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;

    // Datos simulados (puedes cambiarlos)
    private final String USUARIO_DB = "admin";
    private final String PASSWORD_DB = "1234";

    @FXML
    private void handleLogin() throws IOException {
        String user = txtUsuario.getText();
        String pass = txtPassword.getText();

        if (user.equals(USUARIO_DB) && pass.equals(PASSWORD_DB)) {
            abrirVentanaUsuario(user);
        } else {
            // Ventana de Alerta en caso de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Acceso");
            alert.setHeaderText(null);
            alert.setContentText("Credenciales incorrectas. Intente de nuevo.");
            alert.showAndWait();

            txtUsuario.clear();
            txtPassword.clear();
        }
    }

    private void abrirVentanaUsuario(String nombreUsuario) throws IOException {
        // Cargar la nueva vista
        FXMLLoader loader = new FXMLLoader(getClass().getResource("user-view.fxml"));
        Scene scene = new Scene(loader.load(), 300, 250);

        // Pasar información al siguiente controlador
        UserController userController = loader.getController();
        userController.setUsuario(nombreUsuario);

        Stage stage = new Stage();
        stage.setTitle("Perfil de Usuario");
        stage.setScene(scene);
        stage.show();

        // Cerrar la ventana de Login actual
        Stage loginStage = (Stage) txtUsuario.getScene().getWindow();
        loginStage.close();
    }
}