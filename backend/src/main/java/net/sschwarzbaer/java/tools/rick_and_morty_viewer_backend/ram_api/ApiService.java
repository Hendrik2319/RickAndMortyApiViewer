package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private final WebClient webClient;

    public ApiService() {
        this.webClient = WebClient.create("https://rickandmortyapi.com/api");
    }

    private <ResponseType> ResponseType getResponse(@NonNull String uri, @NonNull Class<ResponseType> clazz) {
        ResponseEntity<ResponseType> responseEntity = webClient
                .get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.empty())
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.empty())
                .toEntity(clazz)
                .block();

        if (responseEntity==null) return null;

        HttpStatusCode statusCode = responseEntity.getStatusCode();
        if (statusCode.is4xxClientError()) return null;
        if (statusCode.is5xxServerError()) return null;

        return responseEntity.getBody();
    }

    private RAMCharacterListResponse getCharacterListResponse(@Nullable Integer page) {
        return getResponse(
            "/character" + (page==null ? "" : "?page="+page),
            RAMCharacterListResponse.class
        );
    }

    public Optional<RAMCharacter> getCharacterById(@NonNull Integer id) {
        return Optional.ofNullable(getResponse(
            "/character/"+id,
            RAMCharacter.class
        ));
    }

    // public List<RickAndMortyCharacter> getAllCharacters() {
    //     int pageCount = 2;
    //     for (int page = 1; page < pageCount; page++) {
    //         RickAndMortyCharacterListResponse response = getCharacterListResponse(page);
    //         PageInfo info = response.info();
    //     }
    //     return null;
    // }

    public List<RAMCharacter> getCharactersPage(@Nullable Integer page) {
        RAMCharacterListResponse response = getCharacterListResponse(page);
        return response==null ? List.of() : response.results();
    }
    
}
