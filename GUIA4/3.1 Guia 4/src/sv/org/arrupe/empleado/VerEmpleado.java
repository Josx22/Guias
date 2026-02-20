package sv.org.arrupe.empleado;

import java.sql.*;

public class VerEmpleado {

    public VerEmpleado() throws SQLException, ClassNotFoundException {
        //Se utilizara un try para trabajar posibles errores de MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/modug2", "root", "");
            
            Statement s = conexion.createStatement();

            ResultSet rs = s.executeQuery("SELECT * FROM empleados");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("Codigo")
                        + "\nNombre: " + rs.getString("Nombre")
                        + "\nApellidos: " + rs.getString("Apellidos")
                        + "\nTelefono: " + rs.getString("Telefono"));
                System.out.println("******************************************");
            }

            conexion.close();

        } catch (ClassNotFoundException e1) {
            // Por si el driver falla
            System.out.println("ERROR: Driver no encontrado" + e1.getMessage());
        } catch (SQLException e2) {
            // Error por si el login falla o la query es erronea
            System.out.println("ERROR: Fallo en SQL" + e2.getMessage());
        }
    }

    // Metodo principal, instancia una clase prueba SQL
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new VerEmpleado();
    }
}