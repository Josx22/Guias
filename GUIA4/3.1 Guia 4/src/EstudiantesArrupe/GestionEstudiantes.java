package EstudiantesArrupe;
import java.sql.*;
import javax.swing.JOptionPane;
import sv.org.arrupe.util.*;

public class GestionEstudiantes {
    Conexion con;

    public GestionEstudiantes() {
        try {
            con = new Conexion(); 
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    // --- CRUD ALUMNO ---
    public void insertarAlumno() throws SQLException {
        String cod = JOptionPane.showInputDialog("Código del Alumno:");
        String nom = JOptionPane.showInputDialog("Nombre:");
        String ape = JOptionPane.showInputDialog("Apellido:");
        String eda = JOptionPane.showInputDialog("Edad:");
        String dir = JOptionPane.showInputDialog("Dirección:");

        if (!Validaciones.esNumero(cod) || !Validaciones.esNumero(eda) || Validaciones.esVacio(nom)) {
            JOptionPane.showMessageDialog(null, "Validación Fallida: Revisa que código y edad sean números.");
            return;
        }

        String sql = "INSERT INTO alumno VALUES (" + cod + ",'" + nom + "','" + ape + "'," + eda + ",'" + dir + "')";
        con.setQuery(sql);
        JOptionPane.showMessageDialog(null, "Alumno registrado.");
    }

    // --- CRUD MATERIA ---
    public void insertarMateria() throws SQLException {
        String cod = JOptionPane.showInputDialog("Código de Materia:");
        String nom = JOptionPane.showInputDialog("Nombre Materia:");
        String des = JOptionPane.showInputDialog("Descripción:");

        if (!Validaciones.esNumero(cod) || Validaciones.esVacio(nom)) {
            JOptionPane.showMessageDialog(null, "Datos inválidos.");
            return;
        }

        String sql = "INSERT INTO materia VALUES (" + cod + ",'" + nom + "','" + des + "')";
        con.setQuery(sql);
        JOptionPane.showMessageDialog(null, "Materia registrada.");
    }

    // --- CRUD ALUMNO_MATERIA (Inscripción) ---
    public void inscribirMateria() throws SQLException {
        String codA = JOptionPane.showInputDialog("ID Alumno:");
        String codM = JOptionPane.showInputDialog("ID Materia:");

        if (Validaciones.esNumero(codA) && Validaciones.esNumero(codM)) {
            String sql = "INSERT INTO alumno_materia VALUES (" + codA + "," + codM + ")";
            con.setQuery(sql);
            JOptionPane.showMessageDialog(null, "Inscripción exitosa.");
        }
    }

    // --- REPORTES ---
    public void mostrarTabla(String nombreTabla) throws SQLException {
        String sql = "SELECT * FROM " + nombreTabla;
        con.setRs(sql);
        ResultSet rs = con.getRs();
        String listado = "--- Listado de " + nombreTabla + " ---\n";

        while (rs.next()) {
            if (nombreTabla.equals("alumno")) {
                listado += rs.getInt("cod_alumno") + ": " + rs.getString("Nombre") + " " + rs.getString("Apellido") + "\n";
            } else if (nombreTabla.equals("materia")) {
                listado += rs.getInt("cod_materia") + ": " + rs.getString("Nombre") + "\n";
            } else {
                listado += "Alum_ID: " + rs.getInt(1) + " cursando Mat_ID: " + rs.getInt(2) + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, listado);
    }

    public void materiasDeAlumno() throws SQLException {
        String cod = JOptionPane.showInputDialog("Código del alumno para ver sus materias:");
        String sql = "SELECT m.Nombre FROM materia m " +
                     "JOIN alumno_materia am ON m.cod_materia = am.cod_materia " +
                     "WHERE am.cod_alumno = " + cod;
        
        con.setRs(sql);
        ResultSet rs = con.getRs();
        String res = "Materias para Alumno ID " + cod + ":\n";
        boolean tiene = false;
        while (rs.next()) {
            res += "• " + rs.getString(1) + "\n";
            tiene = true;
        }
        JOptionPane.showMessageDialog(null, tiene ? res : "Sin materias inscritas.");
    }

    public static void main(String[] args) throws SQLException {
        GestionEstudiantes ge = new GestionEstudiantes();
        String[] opciones = {"1. Registrar Alumno", "2. Registrar Materia", "3. Inscribir Alumno a Materia", 
                             "4. Ver Reporte de Alumnos", "5. Ver Reporte Materias Alumno", "6. Salir"};
        
        while (true) {
            String op = (String) JOptionPane.showInputDialog(null, "Menú Estudiantes", "CRUD",
                         JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            
            if (op == null || op.contains("6")) break;
            
            if (op.startsWith("1")) ge.insertarAlumno();
            if (op.startsWith("2")) ge.insertarMateria();
            if (op.startsWith("3")) ge.inscribirMateria();
            if (op.startsWith("4")) ge.mostrarTabla("alumno");
            if (op.startsWith("5")) ge.materiasDeAlumno();
        }
        if (ge.con != null) ge.con.cerrarConexion();
    }
}