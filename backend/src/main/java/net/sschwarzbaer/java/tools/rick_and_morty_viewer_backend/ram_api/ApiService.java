package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api;

import java.util.ArrayList;
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
    public final @NonNull CharacterService characters;
    public final @NonNull LocationService locations;
    public final @NonNull EpisodeService episodes;

    public ApiService() {
        this.webClient = WebClient.create("https://rickandmortyapi.com/api");
        characters = new CharacterService();
        locations = new LocationService();
        episodes = new EpisodeService();
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

    public class CharacterService extends GenericApiService<RAMCharacter, CharacterService.ListType>
    {
        CharacterService() { super("/character", RAMCharacter.class, ListType.class); }
        private static class ListType extends RAMListResponse<RAMCharacter> {}
    }

    public class LocationService extends GenericApiService<RAMLocation, LocationService.ListType>
    {
        LocationService() { super("/location", RAMLocation.class, ListType.class); }
        private static class ListType extends RAMListResponse<RAMLocation> {}
    }

    public class EpisodeService extends GenericApiService<RAMEpisode, EpisodeService.ListType>
    {
        EpisodeService() { super("/episode", RAMEpisode.class, ListType.class); }
        private static class ListType extends RAMListResponse<RAMEpisode> {}
    }

    public interface GenericApiServiceInt<ItemType> {
        List<ItemType> getAll();
        List<ItemType> getPage(@Nullable Integer page);
        Optional<ItemType> getById(int id);
    }

    private class GenericApiService<ItemType, ListResponseType extends RAMListResponse<ItemType>> implements GenericApiServiceInt<ItemType> {

        private final @NonNull String basePath;
        private final @NonNull Class<ItemType> itemClass;
        private final @NonNull Class<ListResponseType> listResponseClass;

        GenericApiService(@NonNull String basePath, @NonNull Class<ItemType> itemClass, @NonNull Class<ListResponseType> listResponseClass) {
            this.basePath = basePath;
            this.itemClass = itemClass;
            this.listResponseClass = listResponseClass;
        }

        private ListResponseType getListResponse(@Nullable Integer page) {
            return getResponse(
                basePath + (page==null ? "" : "?page="+page),
                listResponseClass
            );
        }

        public Optional<ItemType> getById(int id) {
            return Optional.ofNullable(getResponse(
                basePath+"/"+id,
                itemClass
            ));
        }

        public List<ItemType> getAll() {
            int pageCount = 1;
            ArrayList<ItemType> items = new ArrayList<>();
            for (int page = 1; page <= pageCount; page++) {
                ListResponseType response = getListResponse(page);
                if (response!=null) {
                    if (response.info   !=null) pageCount = response.info.pages();
                    if (response.results!=null) items.addAll(response.results);
                }
            }
            return items;
        }

        public List<ItemType> getPage(@Nullable Integer page) {
            ListResponseType response = getListResponse(page);
            return response==null ? List.of() : response.results;
        }
    }
    
}
