package VTTP2022.NUSISS.March21Pokemon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import VTTP2022.NUSISS.March21Pokemon.models.Pokemon;
import VTTP2022.NUSISS.March21Pokemon.services.PokemonService;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping(path="/pokemon")
public class PokemonController {

    private Logger logger = Logger.getLogger(PokemonController.class.getName());

    @Autowired
    PokemonService pokemonSvc;

    @GetMapping(path="/search")
    public String search(
        @RequestParam(name="pokemon_name") String pokemonName, Model model
    )
    {
        logger.log(Level.INFO,">>>>>>>>>requestParam pokemon_name is =%s".formatted(pokemonName));

        Optional<Pokemon> poke = pokemonSvc.findPokemon(pokemonName);

        if(poke.isEmpty()){
            return "404";
        }
        Pokemon validPoke = poke.get();
        logger.log(Level.INFO, "validPoke = %s".formatted(validPoke));

        model.addAttribute("pokemonName", pokemonName.toUpperCase());
        model.addAttribute("pokemon", validPoke);

        return "search_result";
    }

}
