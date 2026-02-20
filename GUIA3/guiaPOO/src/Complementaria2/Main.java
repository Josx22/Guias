package Complementaria2;

import javax.swing.JOptionPane;


public class Main {
    public static void main(String[] args) {
        
        // 1. INSTANCIAMOS LOS OBJETOS
        CalculadoraBasica cbasica = new CalculadoraBasica();
        CalculadoraAvanzada cavanzada = new CalculadoraAvanzada();
        
        // Menú Principal
        String menu = "Seleccione una opción:\n"
                + "1. Suma\n"
                + "2. Resta\n"
                + "3. Multiplicación\n"
                + "4. División\n"
                + "5. Potencia\n"
                + "6. Opuesto\n"
                + "7. Factorial";
                
        String opcionStr = JOptionPane.showInputDialog(menu);
        int op = Integer.parseInt(opcionStr);
        
        double a = 0, b = 0, resultado = 0;
        
        if (op >= 1 && op <= 5) {
            a = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el primer número:"));
            b = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el segundo número (o exponente):"));
        } else if (op == 6 || op == 7) {
            a = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el número:"));
        }

        switch (op) {
            case 1:
                resultado = cbasica.suma(a, b);
                break;
            case 2:
                resultado = cbasica.resta(a, b);
                break;
            case 3:
                resultado = cbasica.multiplicacion(a, b);
                break;
            case 4:
                resultado = cbasica.division(a, b);
                break;
            case 5: 
                resultado = cavanzada.potencia(a, b);
                break;
            case 6: 
                resultado = cavanzada.opuesto(a);
                break;
            case 7: 
                resultado = cavanzada.factorial((int) a);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida");
                return; 
        }

        
        JOptionPane.showMessageDialog(null, "El resultado es: " + resultado);
    }
}