/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apisjava;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author dell
 */
public class APIsJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int respuesta = JOptionPane.NO_OPTION; 
    
       do{
          
        try{
            String nombre_pokemon = JOptionPane.showInputDialog("Ingrese el nombre del pokemon");
            if (nombre_pokemon == null) break;
            CloseableHttpClient httpClient = HttpClients.createDefault();
            
            String ver_pokemon = ("https://pokeapi.co/api/v2/pokemon/" + nombre_pokemon);
            
            HttpGet request = new HttpGet(ver_pokemon);
            
            
            CloseableHttpResponse response = httpClient.execute(request);
            
            
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Respuesta:" + responseBody);
            
            Gson gson = new Gson();
            JsonObject pokemonObject = gson.fromJson(responseBody, JsonObject.class);
            
            String pokemonName= pokemonObject.get("name").getAsString();
            String pokemonType= pokemonObject.getAsJsonArray("types").get(0)
                    .getAsJsonObject().getAsJsonObject("type").get("name")
                    .getAsString();
            int pokemonWeight = pokemonObject.get("weight").getAsInt();
            int pokemonHeight = pokemonObject.get("height").getAsInt();
            
            String message= "pokemon: " + pokemonName + "\n" +
                    "tipo: " + pokemonType + "\n" +
                    "Peso: " + pokemonWeight + "\n"+
                    "Tamaño" + pokemonHeight;
            
            String imageUrl= pokemonObject.getAsJsonObject("sprites").get("front_default").getAsString();
            
            BufferedImage image = ImageIO.read(new URL(imageUrl));
            ImageIcon icon= new ImageIcon(image);
            
            JLabel label = new JLabel("<html>Pokemon: " +pokemonName + "<br>Tipo: " + pokemonType + "<br>Peso: " +  pokemonWeight + "<br>Tamaño: " + pokemonHeight + "</html>", icon, JLabel.CENTER);
            
            JOptionPane.showMessageDialog(null, label, "Informacion del pokemón",
                    JOptionPane.INFORMATION_MESSAGE);
            
            respuesta = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Confirmar", JOptionPane.YES_NO_OPTION);
            
            
            response.close();
            httpClient.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
       while(
               respuesta == JOptionPane.YES_OPTION

               );
        
    }
        
    
}
    
