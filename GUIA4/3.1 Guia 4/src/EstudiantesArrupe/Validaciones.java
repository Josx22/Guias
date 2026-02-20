package EstudiantesArrupe;
import javax.swing.JOptionPane;

public class Validaciones {
    // Valida que el texto no esté vacío
    public static boolean esVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    // Valida que sea un número entero (para Edad y Códigos)
    public static boolean esNumero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}