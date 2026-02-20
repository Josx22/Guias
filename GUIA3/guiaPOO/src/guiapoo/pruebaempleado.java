
package guiapoo;

import javax.swing.JOptionPane;

public class pruebaempleado {
    public static void main(String[] args){
        Empleado emp=new Empleado();
        Profesor pro= new Profesor();
        JOptionPane.showMessageDialog(null, "Ejecutando la clase empleado");
        emp.ingresoDatos();
emp.mostrarDatos();

JOptionPane.showMessageDialog(null, "Ejecutando la clase profesor");
pro.ingreso2();
pro.mostrar2();



    }
}
