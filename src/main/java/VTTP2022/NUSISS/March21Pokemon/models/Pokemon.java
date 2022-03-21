package VTTP2022.NUSISS.March21Pokemon.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Pokemon {
    private static String[] IMAGES = { 
        "sprites", "versions", "generation-ii", "crystal" };

    private String name;
    private List<String> images = new LinkedList<>();
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getImages() {
        return images;
    }
    public void setImages(List<String> images) {
        this.images = images;
    }

    public static Pokemon create(String json) throws IOException{
        
        Logger logger = Logger.getLogger(Pokemon.class.getName());
        Pokemon p = new Pokemon();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
           JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            p.name = o.getString("name");
            logger.log(Level.INFO, "pokemon name logged in create method: %s".formatted(p.name));
            for (String i : IMAGES) {
                o=o.getJsonObject(i);
            }
            List<String> l = o.values().stream()
            .filter(v -> v!= null)
            .map(v ->v.toString().replaceAll("\"", ""))
            .toList();
            p.setImages(l);
            logger.log(Level.INFO, "pokemon images list logged in create method: %s".formatted(l));
           
        }
        return p;

    }


    

}
