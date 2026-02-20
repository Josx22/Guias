
    package Complementaria1;

    public class Materias {
        public static void main(String [] args){
            Estudiante estudiante = new Estudiante("Josue", "Palacios");
            
            estudiante.asignarMaterias("Matematicas","Sociales","Ingles","Lenguaje","Quimica");
            
            estudiante.MostrarInformacion();
        }
       
    }
