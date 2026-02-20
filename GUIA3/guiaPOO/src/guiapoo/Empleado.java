
package guiapoo;

import javax.swing.JOptionPane;

public class Empleado {
    private String nombre;
    private String apellido;
    
    public void mostrarDatos(){
        JOptionPane.showConfirmDialog(null, nombre +" "+apellido);
    }
    public void ingresoDatos(){
        nombre=JOptionPane.showInputDialog("Ingrese su nombre");
        apellido=JOptionPane.showInputDialog("Ingrese su apellido");
    }
    
}
    class Profesor extends Empleado {
        int sueldo;
        public void mostrar2(){
            mostrarDatos();
            JOptionPane.showMessageDialog(null, sueldo);
          
        }
        public void ingreso2(){
            ingresoDatos();
            String s=JOptionPane.showInputDialog("ingrese su sueldo");
            sueldo= Integer.parseInt(s);
        }
        
        
        }
    

