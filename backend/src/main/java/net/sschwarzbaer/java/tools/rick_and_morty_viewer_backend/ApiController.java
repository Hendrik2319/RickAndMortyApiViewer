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

    @GetMapping( { "/characters", "/characters/", "/characters/{page}" })
    public ResponseEntity<List<RAMCharacter>> getCharactersPage(@PathVariable @Nullable String page) {
        if (page!=null && page.toLowerCase().equals("all"))
            return ResponseEntity.ok(apiService.getAllCharacters());
        
        Integer pageNo = null;
        if (page!=null)
            try { pageNo = Integer.parseInt(page); }
            catch (NumberFormatException e) { return ResponseEntity.badRequest().build(); }

        return ResponseEntity.ok(apiService.getCharactersPage(pageNo));
    }

    @GetMapping("/character/{id}")
    public ResponseEntity<RAMCharacter> getCharacter(@PathVariable @NonNull Integer id) {
        return ResponseEntity.of(apiService.getCharacterById(id));
    }
    
}
