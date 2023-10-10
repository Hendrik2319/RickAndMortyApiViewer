package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api.ApiService;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api.ApiService.GenericApiServiceInt;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api.RAMCharacter;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api.RAMEpisode;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.rest.Converter;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.rest.Converter.GenericConverterInt;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.rest.RestCharacter;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.rest.RestEpisode;

@RestController
@RequestMapping("api")
public class ApiController {

    private final GenericController<RestCharacter, RAMCharacter> characterController;
    private final GenericController<RestEpisode  , RAMEpisode  > episodeController;

    public ApiController(ApiService apiService) {
        characterController = new GenericController<>("Character", Converter.characters, apiService.characters);
        episodeController   = new GenericController<>("Episode"  , Converter.episodes  , apiService.episodes  );
    }


    @GetMapping( { "/character", "/character/" })
    public ResponseEntity<List<RestCharacter>> getCharactersPage(@RequestParam @Nullable String page) { return characterController.getItemPage( page ); }

    @GetMapping( { "/episode", "/episode/" })
    public ResponseEntity<List<RestEpisode  >> getEpisodesPage  (@RequestParam @Nullable String page) { return   episodeController.getItemPage( page ); }

    @GetMapping( { "/character/{id}" } )
    public ResponseEntity<RestCharacter> getCharacter(@PathVariable @NonNull String id) { return characterController.getItem( id ); }

    @GetMapping( { "/episode/{id}" } )
    public ResponseEntity<RestEpisode  > getEpisode  (@PathVariable @NonNull String id) { return   episodeController.getItem( id ); }


    @RequiredArgsConstructor
    private static class GenericController<RestType, RAMType>
    {
        private final @NonNull String itemLabel;
        private final @NonNull GenericConverterInt<RestType, RAMType> converter;
        private final @NonNull GenericApiServiceInt<RAMType> apiService;

        ResponseEntity<List<RestType>> getItemPage( @Nullable String page )
        {
            Integer pageNo = null;
            if (page!=null) {
                if (page.equalsIgnoreCase("all"))
                    return ResponseEntity.ok(
                        converter.convert(
                            apiService.getAll(),
                            "get%ssPage( all )".formatted(itemLabel)
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
                converter.convert(
                    apiService.getPage(pageNo),
                    "get%ssPage( %s )".formatted(itemLabel, pageNo==null ? "" : pageNo)
                )
            );
        }

        ResponseEntity<RestType> getItem( @NonNull String id )
        {
            try {
                return ResponseEntity.of(
                    converter.convert(
                        apiService.getById(
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
    
}
