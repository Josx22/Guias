package Animales;

import javax.swing.JOptionPane;

public class Animal {
    protected String nombre;
    protected int edad;
    protected String alimento;
    
    public Animal(){
    }
    public Animal(String nombre, int edad, String alimento){
        this.nombre = nombre;
        this.edad = edad;
        this.alimento = alimento;
    }
    public void ingresoDatos(){
        nombre=JOptionPane.showInputDialog("Ingrese el Nombre");
        edad=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad"));
        alimento=JOptionPane.showInputDialog("Ingrese el alimento");
    }
    public void mostrarDatos(){
        System.out.println("Su nombre es "+nombre);
        System.out.println("Su nombre es "+edad);
        System.out.println("Su nombre es "+alimento);
        System.out.println("**************************"); 
    }   
}
class Perro extends Animal{
    private String pelaje;
    
    public Perro(String pelaje){
        this.pelaje = pelaje;
    }
    public void datosPerro(){
    pelaje= JOptionPane.showInputDialog("Ingrese el pelaje del perro");
}
}
 class Main extends Animal{
public static void main(String[] args) {

    String menu = "Seleccione una opción de mascota:\n"
                + "1. Perro\n"
                + "2. Gato\n"
                + "3. Hamster\n"
                + "4. Gallo\n";    
}
}
