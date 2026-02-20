
package guiapoo;

import java.text.DecimalFormat;

/**
 *
 * @author dell
 */
public class Tiempo1 extends Object {
    private int hora;
    private int minutos;
    private int segundos;
    
    public Tiempo1(){
        establecerHora(0,0,0);
    }
    
    public void establecerHora(int h, int m, int s){
        hora = ((h >= 0 && h < 24)? h: 0);
        minutos= ((m >= 0 && m < 60)? m: 0);
        segundos = ((s >= 0 && s <60)? s: 0);
    }
    public String aStringUniversal(){
        DecimalFormat dosDigitos = new DecimalFormat("00");
        return dosDigitos.format(hora)+ ":" +
                dosDigitos.format(minutos)+ ":" + dosDigitos.format(segundos);
    }
    public String aStringEstandar(){
        DecimalFormat dosDigitos = new DecimalFormat("00");
        return((hora == 12 || hora == 0)? 12: hora % 12) + ":" + 
                dosDigitos.format(minutos) + ":" + dosDigitos.format(segundos) +
                (hora <12 ? "AM": "PM");
    }
}
