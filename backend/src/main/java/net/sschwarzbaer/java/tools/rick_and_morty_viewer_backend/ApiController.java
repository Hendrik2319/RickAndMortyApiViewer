package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping( { "/character", "/character/" })
    public ResponseEntity<List<RestCharacter>> getCharactersPage(@RequestParam @Nullable String page) {
        Integer pageNo = null;
        if (page!=null) {
            if (page.equalsIgnoreCase("all"))
                return ResponseEntity.ok(
                    Converter.convert(
                        apiService.characters.getAll(),
                        "getCharactersPage( all )"
                    )
                );
            
            try {
                pageNo = Integer.parseInt(page);
            }
            catch (NumberFormatException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok(
            Converter.convert(
                apiService.characters.getPage(pageNo),
                "getCharactersPage( %s )".formatted(pageNo==null ? "" : pageNo)
            )
        );
    }

    @GetMapping( { "/character/{id}" } )
    public ResponseEntity<RestCharacter> getCharacter(@PathVariable String id) {
        try {
            return ResponseEntity.of(
                Converter.convert(
                    apiService.characters.getById(
                        Integer.parseInt(id)
                    ),
                    "getCharacter( %s )".formatted(id)
                )
            );
        }
        catch (NumberFormatException e) {}
        
        return ResponseEntity.badRequest().build();
    }
    
}
