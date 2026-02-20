package sv.org.arrupe.www;

public class Empezar {
    public static void main(String args[]){
        Logueo login = new Logueo();
        login.setVisible(true); // <--- TE FALTA ESTA LÍNEA OBLIGATORIA
        login.setLocationRelativeTo(null);
    }
}
