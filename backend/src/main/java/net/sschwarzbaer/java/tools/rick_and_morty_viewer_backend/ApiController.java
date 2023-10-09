package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api.ApiService;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.rest.Converter;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.rest.RestCharacter;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping( { "/characters", "/characters/" })
    public List<RestCharacter> getCharactersPage1() {
        return Converter.convert(
            apiService.characters.getPage(null),
            "getCharactersPage1()"
        );
    }

    @GetMapping( "/characters/{page}" )
    public ResponseEntity<List<RestCharacter>> getCharactersPageN(@PathVariable String page) {
        try {
            return ResponseEntity.ok(
                Converter.convert(
                    apiService.characters.getPage(
                        Integer.parseInt(page)
                    ),
                    "getCharactersPageN(\"%s\")".formatted(page)
                )
            );
        }
        catch (NumberFormatException e) {}
        
        return ResponseEntity.badRequest().build();
    }

    @GetMapping( "/characters/all" )
    public List<RestCharacter> getAllCharacters() {
        return Converter.convert(
            apiService.characters.getAll(),
            "getAllCharacters()"
        );
    }

    @GetMapping( { "/character", "/character/", "/character/{id}" } )
    public ResponseEntity<RestCharacter> getCharacter(@PathVariable @Nullable String id) {
        if (id!=null)
            try {
                return ResponseEntity.of(
                    Converter.convert(
                        apiService.characters.getById(
                            Integer.parseInt(id)
                        ),
                        "getCharacter(\"%s\")".formatted(id)
                    )
                );
            }
            catch (NumberFormatException e) {}
        
        return ResponseEntity.badRequest().build();
    }
    
}
