package sv.arrupe.apinasa.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sv.arrupe.apinasa.model.ApodResponse;
import sv.arrupe.apinasa.service.NasaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MainController {

    @FXML
    private DatePicker datePicker;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblDescripcion;
    @FXML
    private ImageView imageView;
    @FXML
    private ProgressIndicator progressIndicator; // Asegúrate de agregarlo en Scene Builder / FXML

    private final NasaService nasaService = new NasaService();
    // Formateador para mostrar: "12 de febrero de 2026"
    private final DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es"));

    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
       // progressIndicator.setVisible(false); // Oculto al inicio
    }

    @FXML
    private void buscarImagen() {
        LocalDate fechaSeleccionada = datePicker.getValue();

        // 1. VALIDACIÓN DE FECHA MÍNIMA (16 de junio de 1995)
        LocalDate fechaMinima = LocalDate.of(1995, 6, 16);
        if (fechaSeleccionada.isBefore(fechaMinima)) {
            mostrarAlerta("Fecha no válida", "La NASA solo tiene registros desde el 16 de junio de 1995.");
            return;
        }

        // 2. ACTIVAR INDICADOR DE CARGA
        progressIndicator.setVisible(true);
        imageView.setImage(null); // Limpiamos la imagen anterior mientras carga

        // 3. EJECUCIÓN EN SEGUNDO PLANO (Task)
        Task<ApodResponse> task = new Task<>() {
            @Override
            protected ApodResponse call() throws Exception {
                // Hacemos la petición a la API
                return nasaService.getApod(fechaSeleccionada.toString());
            }
        };

        // CUANDO LA TAREA TIENE ÉXITO
        task.setOnSucceeded(event -> {
            ApodResponse apod = task.getValue();
            progressIndicator.setVisible(false);

            // Título con formato personalizado
            String fechaFormateada = fechaSeleccionada.format(formateador);
            lblTitulo.setText(apod.getTitle() + " - " + fechaFormateada);

            lblDescripcion.setText(apod.getExplanation());

            if ("image".equals(apod.getMedia_type())) {
                Image image = new Image(apod.getUrl(), true); // true = carga asíncrona de imagen
                imageView.setImage(image);
            } else {
                lblDescripcion.setText("TIPO: VIDEO. Enlace: " + apod.getUrl() + "\n\n" + apod.getExplanation());
                mostrarAlerta("Contenido de video", "Este registro es un video y no puede mostrarse aquí.");
            }
        });

        // CUANDO LA TAREA FALLA (Errores de conexión, etc)
        task.setOnFailed(event -> {
            progressIndicator.setVisible(false);
            Throwable e = task.getException();
            mostrarAlerta("Error", "No se pudo obtener la información de la NASA: " + e.getMessage());
            e.printStackTrace();
        });

        // Iniciamos el hilo
        new Thread(task).start();
    }

    // 4. MÉTODO PARA EL BOTÓN "HOY"
    @FXML
    private void cargarHoy() {
        datePicker.setValue(LocalDate.now());
        buscarImagen();
    }

    // Método auxiliar para alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}