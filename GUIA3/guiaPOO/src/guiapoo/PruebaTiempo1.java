package guiapoo;

import javax.swing.JOptionPane;

public class PruebaTiempo1 {
    public static void main(String args[]){
        Tiempo1 tiempo = new Tiempo1();
            
        String salida= "Hora universal es: " +
                tiempo.aStringUniversal() + "\n Hora estandar inicial es: "+
                tiempo.aStringEstandar();
        
        tiempo.establecerHora(13,27,6);
        salida += "\n \n Hora universal despues de establecerHora es: " + 
                tiempo.aStringUniversal() +
                "\nHora estandar despues de establecerHora es: " +tiempo.aStringEstandar();
        
        tiempo.establecerHora(99,99,99);
        salida += "\n\nDespues de intentar ajustes invalidos: " +
                "\n Hora universal: "+ tiempo.aStringUniversal()+
                "\n Hora estandar: "+ tiempo.aStringEstandar();
        JOptionPane.showMessageDialog(null, salida, "prueba de la clase Tiempo1", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
    
}
