
package guiapoo;

public class Arboles {
    public Arboles(){
    System.out.println("Arbol Generico:");
    }
    public Arboles(String tipo){
    System.out.println("Un arbol de tipo "+ tipo);
    }
    public Arboles(int altura){
        System.out.println("Un arbol con aprox: " + altura + " metros de altura");
    }
    public Arboles(int altura, String tipo){
       System.out.println("Un "+ tipo + " de " +altura+" metros");
    }
    public static void main(String args[]){
        Arboles arbol1 = new Arboles(4);
        Arboles arbol2 = new Arboles("pino");
        Arboles arbol3 = new Arboles();
        Arboles arbol4 = new Arboles(5,"Arce");
    }
}
