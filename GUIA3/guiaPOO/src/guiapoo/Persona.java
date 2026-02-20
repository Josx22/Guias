
import javax.swing.JOptionPane;


public class Persona {
    private String nombre;
    private String apellido;
    private String edad;
    
    public Persona(){
        nombre="Josué";
        apellido="Palacios";
        edad="18";
    }
    public Persona(String nombre, String apellido, String edad){
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
    public void ingresoDatos(){
        nombre=JOptionPane.showInputDialog("Ingrese el Nombre");
        apellido=JOptionPane.showInputDialog("Ingrese su apellido");
        edad=JOptionPane.showInputDialog("Ingrese su edad");
        
    }
    public void mostrarDatos(){
        System.out.println("Su nombre es "+nombre);
        System.out.println("Su nombre es "+apellido);
        System.out.println("Su nombre es "+edad);
        System.out.println("**************************");
    }   
    public static void main(String args[]){
        Persona obj1= new Persona();
       Persona obj2=new Persona("Samuel","Argueta","18");
       obj1.mostrarDatos();
       obj1.ingresoDatos();
       obj1.mostrarDatos();
       obj2.mostrarDatos();
       obj1.apellido="Casoverde";
       obj1.mostrarDatos();
    }
}
