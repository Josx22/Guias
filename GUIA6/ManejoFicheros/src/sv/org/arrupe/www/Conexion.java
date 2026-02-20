
package sv.org.arrupe.www;
import java.sql.*;
public class Conexion {
private Connection conexion = null;
private Statement s = null;
private ResultSet rs = null;
private String query = "";
// Constructor
public Conexion() throws SQLException {
try {
// Obtenemos el driver para MySQL
Class.forName("com.mysql.cj.jdbc.Driver");
// Se obtiene una conexión con la base de datos
conexion =

DriverManager.getConnection("jdbc:mysql://localhost/guijava", "root",
"");

// Permite ejecutar sentencias SQL sin parámetros
s = conexion.createStatement();
} catch (ClassNotFoundException e1) {
// Error si no se puede leer el driver de MySQL
System.out.println("ERROR: No se encuentra el driver de laBD: " + e1.getMessage());
}
}
// Método que permite obtener los valores del ResultSet
public ResultSet getRs() {
return rs;
}
// Método que permite fijar la tabla resultado de la consulta SQLrealizada
public void setRs(String consulta) {
try {
// Creamos el Statement con el tipo de ResultSet deseado
s =

conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_READ_ONLY);

this.rs = s.executeQuery(consulta);
} catch (SQLException e2) {
System.out.println("ERROR: Fallo en SQL: " + e2.getMessage());
}
}

// Método que recibe un SQL como parámetro que sea un update, insert,o delete
public void setQuery(String query) throws SQLException {
this.s.executeUpdate(query);
}
// Método que cierra la conexión
public void cerrarConexion() throws SQLException {
conexion.close();
}
ResultSet getRs(String string) {
try {
// Creamos el Statement con el tipo de ResultSet deseado
Statement statement =
conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_READ_ONLY);
return statement.executeQuery(string);
} catch (SQLException e) {
System.out.println("ERROR: Fallo en SQL: " + e.getMessage());
return null; // o lanza una excepción, dependiendo de tu manejo de errores
}
}
}