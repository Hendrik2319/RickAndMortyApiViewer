package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
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

    private final ApiService apiService;

    @GetMapping( { "/characters", "/characters/" })
    public List<RAMCharacter> getCharactersPage1() {
        return apiService.getCharactersPage(null);
    }

    @GetMapping( "/characters/{page}" )
    public ResponseEntity<List<RAMCharacter>> getCharactersPageN(@PathVariable String page) {
        try { return ResponseEntity.ok(apiService.getCharactersPage(Integer.parseInt(page))); }
        catch (NumberFormatException e) {}
        
        return ResponseEntity.badRequest().build();
    }

    @GetMapping( "/characters/all" )
    public List<RAMCharacter> getAllCharacters() {
        return apiService.getAllCharacters();
    }

    @GetMapping( { "/character", "/character/", "/character/{id}" } )
    public ResponseEntity<RAMCharacter> getCharacter(@PathVariable @Nullable String id) {
        if (id!=null)
            try { return ResponseEntity.of(apiService.getCharacterById(Integer.parseInt(id))); }
            catch (NumberFormatException e) {}
        
        return ResponseEntity.badRequest().build();
    }
    
}
