package VTTP2022.NUSISS.March21Pokemon.services;

import java.util.logging.Logger;
import java.util.Optional;
import java.util.logging.Level;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import VTTP2022.NUSISS.March21Pokemon.models.Pokemon;

@Service
public class PokemonService {
    
    private Logger logger = Logger.getLogger(PokemonService.class.getName());

    private static final String URL = "https://pokeapi.co/api/v2/pokemon/%s";

    public Optional<Pokemon> findPokemon(String pokemonName){
        String urlWithPoke = URL.formatted(pokemonName);

        RequestEntity<Void> req = RequestEntity
        .get(urlWithPoke)
        .accept(MediaType.APPLICATION_JSON)
        .build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;

        try {
            resp = template.exchange(req, String.class);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        
        if (resp.getStatusCodeValue()>=400){
            return Optional.empty();
        }

        try {
            Pokemon pokemon = Pokemon.create(resp.getBody());
            return Optional.of(pokemon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();


    }


}
