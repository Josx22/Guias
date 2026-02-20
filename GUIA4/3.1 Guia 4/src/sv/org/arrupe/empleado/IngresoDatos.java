
package sv.org.arrupe.empleado;

import com.mysql.cj.protocol.Resultset;
import static java.lang.Boolean.TRUE;
import java.sql.*;
import javax.swing.JOptionPane;
import sv.org.arrupe.util.*;

public class IngresoDatos {
    private int id;
    private String ids;
    private String nombre;
    private String apellido;
    private String telefono;

    private Connection conexion;
    private ResultSet rs;
    private Statement s;
    
     public IngresoDatos() {
        //Iniciamos con un try
        try {
            // Cargar el driver (Para conectores nuevos 8.0+ usa: com.mysql.cj.jdbc.Driver)
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection("jdbc:mysql://localhost/modug2", "root", "");

            s = conexion.createStatement();

            //Declaramos el metodo para ingresar los valores
            ingreso();
            
            s.executeUpdate("Insert into Empleados values (" + id + ",\"" + nombre + "\",\"" + apellido + "\",\"" + telefono + "\")");

            JOptionPane.showMessageDialog(null, "Persona ingresada correctamente");

        } catch (ClassNotFoundException e1) {
            System.out.println("ERROR: No encuentro el driver de la BD: " + e1.getMessage());
            System.exit(0);
        } catch (SQLException e2) {
            System.out.println("ERROR: Fallo en SQL: " + e2.getMessage());
            System.exit(0);
        }
    }

    public void ingreso() {
        ids = JOptionPane.showInputDialog("Ingrese el ID");
        id = Integer.parseInt(ids);

        nombre = JOptionPane.showInputDialog("Ingrese el Nombre");
        apellido = JOptionPane.showInputDialog("Ingrese el apellido");
        telefono = JOptionPane.showInputDialog("Ingrese el telefono");

        do {
            if (MatchTelephone.compareTelephone(telefono) == TRUE) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Numero de telefono invalido");
                telefono = JOptionPane.showInputDialog("Ingrese telefono valido");
            }
        } while (true);
    }

    public void mostrardatos() throws SQLException {
        rs = s.executeQuery("SELECT * FROM Empleados");
        while (rs.next()) {
            JOptionPane.showMessageDialog(null, "ID: " + rs.getString("Codigo")
                    + "\nNombre: " + rs.getString("Nombre")
                    + "\nApellidos: " + rs.getString("Apellidos")
                    + "\nnTelefono: " + rs.getString("Telefono"));
        }
    }

    public void cierreconexion() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    public static void main(String[] args) throws SQLException {
        IngresoDatos ing = new IngresoDatos();

        ing.mostrardatos();
        ing.cierreconexion();
    }
}
