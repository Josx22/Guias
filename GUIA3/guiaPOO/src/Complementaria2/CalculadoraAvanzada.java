package Complementaria2;

public class CalculadoraAvanzada {

    public CalculadoraAvanzada() {}

    public double potencia(double base, double exponente) {
        return Math.pow(base, exponente);
    }

    public double opuesto(double numero) {
        return -numero;
    }

    public long factorial(int numero) {
        if (numero < 0) return 0;
        long fact = 1;
        for (int i = 1; i <= numero; i++) {
            fact = fact * i;
        }
        return fact;
    }
}