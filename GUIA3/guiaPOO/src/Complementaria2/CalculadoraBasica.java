package Complementaria2;

public class CalculadoraBasica {
    
    public CalculadoraBasica() {}

    public double suma(double a, double b) {
        return a + b;
    }

    public double resta(double a, double b) {
        return a - b;
    }

    public double multiplicacion(double a, double b) {
        return a * b;
    }

    public double division(double a, double b) {
        if (b == 0) {
            System.out.println("Error: No se puede dividir por cero");
            return 0;
        }
        return a / b;
    }
}