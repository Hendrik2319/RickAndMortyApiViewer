package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api.ApiService;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api.RAMCharacter;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService rickAndMortyCharacterService;

    // @GetMapping("/characters/all")
    // public List<RickAndMortyCharacter> getAllCharacters(@PathVariable @Nullable String page) {
    //     return rickAndMortyCharacterService.getAllCharacters();
    // }

    @GetMapping("/characters")
    public List<RAMCharacter> getCharactersPage() {
        return rickAndMortyCharacterService.getCharactersPage(null);
    }

    @GetMapping("/characters/{page}")
    public List<RAMCharacter> getCharactersPageN(@PathVariable Integer page) {
        return rickAndMortyCharacterService.getCharactersPage(page);
    }

    @GetMapping("/character/{id}")
    public ResponseEntity<RAMCharacter> getCharacter(@PathVariable @NonNull Integer id) {
        return ResponseEntity.of(rickAndMortyCharacterService.getCharacterById(id));
    }
    
}
