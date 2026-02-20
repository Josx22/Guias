package com.example.formularioingreso;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.Period;

public class HelloController {
    @FXML
    private TextField txtNombre;

    @FXML
    private DatePicker dpNacimiento;

    @FXML
    private ComboBox<String> cbBachillerato;

    @FXML
    private Label lblResultado;

    @FXML
    public void initialize(){
        cbBachillerato.getItems().addAll(
                "General",
                "Contador",
                "Electronica",
                "Software"
        );
    }
    @FXML
    private void procesarFormulario(){
        String nombre = txtNombre.getText();
        LocalDate nacimiento = dpNacimiento.getValue();
        String bachillerato = cbBachillerato.getValue();

        if(nombre.isEmpty() || nacimiento == null || bachillerato == null){
                lblResultado.setText("Complete todos los campos");
        }
        int edad = Period.between(nacimiento, LocalDate.now()).getYears();

        lblResultado.setText(
                "Bienvenido " + nombre + "\n" +
                        "Tu edad es " + edad + " años\n"+
                        "Estudiante del bachillerato de " + bachillerato
        );
    }

}
