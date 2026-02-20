package com.example.formularioingreso;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CalculadoraController {

    @FXML private TextField txtNumero;
    @FXML private ListView<Double> lvNumeros;
    @FXML private ComboBox<String> cbOperacion;
    @FXML private Label lblResultado;

    private ObservableList<Double> listaNumeros = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Llenar el ComboBox con operaciones básicas y científicas
        cbOperacion.getItems().addAll(
                "Sumar (+)", "Restar (-)", "Multiplicar (*)", "Dividir (/)",
                "Seno (sin)", "Coseno (cos)", "Raíz Cuadrada (sqrt)"
        );
        lvNumeros.setItems(listaNumeros);
    }

    @FXML
    private void agregarNumero() {
        try {
            double num = Double.parseDouble(txtNumero.getText());
            listaNumeros.add(num);
            txtNumero.clear();
            txtNumero.requestFocus();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Ingrese un número válido.");
        }
    }

    @FXML
    private void ejecutarOperacion() {
        if (listaNumeros.isEmpty()) {
            mostrarAlerta("Error", "La lista de números está vacía.");
            return;
        }

        String op = cbOperacion.getValue();
        if (op == null) {
            mostrarAlerta("Error", "Seleccione una operación.");
            return;
        }

        double resultado = listaNumeros.get(0);

        // Lógica para n números
        for (int i = 1; i < listaNumeros.size(); i++) {
            double n = listaNumeros.get(i);
            switch (op) {
                case "Sumar (+)" -> resultado += n;
                case "Restar (-)" -> resultado -= n;
                case "Multiplicar (*)" -> resultado *= n;
                case "Dividir (/)" -> {
                    if (n != 0) resultado /= n;
                    else { mostrarAlerta("Error", "División por cero."); return; }
                }
            }
        }

        // Si es una operación científica, se aplica al resultado de la acumulación
        // o se puede aplicar individualmente, pero lo común es al total:
        switch (op) {
            case "Seno (sin)" -> resultado = Math.sin(Math.toRadians(resultado));
            case "Coseno (cos)" -> resultado = Math.cos(Math.toRadians(resultado));
            case "Raíz Cuadrada (sqrt)" -> resultado = Math.sqrt(resultado);
        }

        lblResultado.setText("Resultado: " + resultado);
    }

    @FXML
    private void limpiarTodo() {
        listaNumeros.clear();
        lblResultado.setText("Resultado: ");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}