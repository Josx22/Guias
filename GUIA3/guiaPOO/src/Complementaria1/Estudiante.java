
package Complementaria1;

public class Estudiante {
    String nombre;
    String apellido;
    private String materia1;
    private String materia2;
    private String materia3;
    private String materia4;
    private String materia5;
    
    public Estudiante(String nombre, String apellido){
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public void asignarMaterias(String m1, String m2, String m3, String m4, String m5){
        this.materia1 = m1;
        this.materia2=  m2;
        this.materia3 = m3;
        this.materia4 = m4;
        this.materia5 = m5;
    }
    public void MostrarInformacion(){
        System.out.println("ESTUDIANTE");
        System.out.println("Nombre: " +this.nombre);
        System.out.println("Apellido: " +this.apellido);
        System.out.println("1. " +this.materia1);
        System.out.println("2. " +this.materia2);
        System.out.println("3. " +this.materia3);
        System.out.println("4. " +this.materia4);
        System.out.println("5. " +this.materia5);
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
}
    