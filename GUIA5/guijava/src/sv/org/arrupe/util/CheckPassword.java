
package sv.org.arrupe.util;

public class CheckPassword {
    public boolean verificarPassword(char passArray[]) {
        for (int i = 0; i < passArray.length; i++) {
            char c = passArray[i];
            // OJO AQUI: el signo '!' significa NOT (NO es letra ni dígito)
            if (!Character.isLetterOrDigit(c)) { 
                return false; 
            }
        }
        return true;
    }
}
