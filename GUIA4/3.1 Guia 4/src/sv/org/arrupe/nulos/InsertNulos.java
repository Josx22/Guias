package sv.org.arrupe.nulos;
import java.sql.ResultSet;
import java.sql.SQLException;
import sv.org.arrupe.util.Conexion;

/**
 *
 * @author DELL
 */
public class InsertNulos {
    public static void main(String[] args) throws SQLException {
        
        Conexion con = new Conexion();
        

        String sql = "select Nombre from Alumno ";
        ResultSet rs ;
        
        con.setRs(sql);
        rs = con.getRs();
        
        String nombre;
        
        while (rs.next()){
            nombre = rs.getString(1);
            
            if (nombre == null) {
                System.out.println("Nombre 'Null': " + nombre);
            } else if (nombre.equals("")) {
                System.out.println("Nombre Vacio: " + nombre);
            } else {
                System.out.println("Nombre Con Datos: " + nombre);
            }
        }
        
        con.cerrarConexion();
    }
}