package sv.arrupe.tienda;

import sv.arrupe.tienda.dao.ProductoDAO;
import sv.arrupe.tienda.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, Integer> colId;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;

    @FXML private TextField txtBusqueda;
    @FXML private Label lblTotales;
    @FXML private Button btnActualizar;

    private ProductoDAO dao = new ProductoDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colNombre.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        colPrecio.setCellValueFactory(data ->
                new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrecio()).asObject());
        colStock.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStock()).asObject());

        cargarDatos();
    }

    @FXML
    private void agregarProducto() {
        Producto p = new Producto(
                txtNombre.getText(),
                Double.parseDouble(txtPrecio.getText()),
                Integer.parseInt(txtStock.getText())
        );

        dao.insertar(p);
        cargarDatos();
    }



    private boolean validarCampos() {
        try {
            if (txtNombre.getText().isEmpty() || txtPrecio.getText().isEmpty() || txtStock.getText().isEmpty()) {
                mostrarAlerta("Error", "Campos vacíos", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
                return false;
            }
            double precio = Double.parseDouble(txtPrecio.getText());
            int stock = Integer.parseInt(txtStock.getText());

            if (precio < 0 || stock < 0) {
                mostrarAlerta("Error", "Valores negativos", "Precio y stock deben ser positivos", Alert.AlertType.ERROR);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Formato inválido", "Precio debe ser decimal y Stock entero", Alert.AlertType.ERROR);
            return false;
        }
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    // Lógica para cargar totales (Punto 6)
    private void actualizarResumen() {
        lblTotales.setText(dao.obtenerTotales());
    }

    // Punto 2: Limpiar campos
    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtPrecio.clear();
        txtStock.clear();
        tablaProductos.getSelectionModel().clearSelection();
    }

    // Punto 3: Confirmación antes de eliminar
    @FXML
    private void eliminarProducto() {
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setContentText("¿Está seguro de eliminar " + seleccionado.getNombre() + "?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                dao.eliminar(seleccionado.getId());
                cargarDatos();
                limpiarCampos();
            }
        }
    }

    // Punto 4: Update (Cargar datos y Actualizar)
    @FXML
    private void seleccionarDeTabla() {
        Producto p = tablaProductos.getSelectionModel().getSelectedItem();
        if (p != null) {
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio()));
            txtStock.setText(String.valueOf(p.getStock()));
        }
    }

    @FXML
    private void actualizarProducto() {
        Producto p = tablaProductos.getSelectionModel().getSelectedItem();
        if (p != null && validarCampos()) {
            p.setNombre(txtNombre.getText());
            p.setPrecio(Double.parseDouble(txtPrecio.getText()));
            p.setStock(Integer.parseInt(txtStock.getText()));
            dao.actualizar(p);
            cargarDatos();
            limpiarCampos();
        }
    }

    // Punto 5: Buscar
    @FXML
    private void buscarProducto() {
        String nombre = txtBusqueda.getText();
        ObservableList<Producto> lista = FXCollections.observableArrayList(dao.buscarPorNombre(nombre));
        tablaProductos.setItems(lista);
    }

    // Modificar cargarDatos original para incluir el resumen
    private void cargarDatos() {
        ObservableList<Producto> lista = FXCollections.observableArrayList(dao.listar());
        tablaProductos.setItems(lista);
        actualizarResumen(); // Llama a los totales
    }
}
