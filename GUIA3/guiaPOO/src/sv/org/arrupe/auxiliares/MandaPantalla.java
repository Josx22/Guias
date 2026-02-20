
package sv.org.arrupe.auxiliares;

import sv.org.arrupe.escritura.Pantalla;

public class MandaPantalla {
    public static void main(String args[]){
        Pantalla primera = new Pantalla();
        primera.conSalto("Esto es un renglon CON salto de linea");
        primera.conSalto("Esta linea contiene salto");
        primera.sinSalto("linea continua");
        primera.sinSalto("Linea continua");
        primera.conSalto("Esta linea si tien salto");
        primera.sinSalto("termina sin salto");
        System.out.println("Se terminaron las funciones");
    }
}
