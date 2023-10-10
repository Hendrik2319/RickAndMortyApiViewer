package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;

import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api.RAMCharacter;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api.RAMEpisode;

public class Converter
{
    public static final @NonNull GenericConverter<RestEpisode, RAMEpisode> episodes = new GenericConverter<>()
    {
        @Override
        public RestEpisode convert(RAMEpisode episode, String source)
        {
            if (episode==null)
                return null;

            source += ".Episode{ id:%s }".formatted(episode.id());

            String expectedEpisodeURL = "https://rickandmortyapi.com/api/episode/%d".formatted(episode.id());
            if (!expectedEpisodeURL.equals(episode.url()))
                System.err.printf("%s: Episode-Url \"%s\" does not equal expected value \"%s\".%n", source, episode.url(), expectedEpisodeURL);

            return new RestEpisode(
                episode.id(),
                episode.name(),
                episode.air_date(),
                episode.episode(),
                getIDs(episode.characters(), "https://rickandmortyapi.com/api/character/", "Character", source+".characters"),
                episode.created()
            );
        }
    };
    
    public static final @NonNull GenericConverter<RestCharacter, RAMCharacter> characters = new GenericConverter<>()
    {
        @Override
        public RestCharacter convert(RAMCharacter character, String source)
        {
            if (character==null)
                return null;

            source += ".Character{ id:%s }".formatted(character.id());

            String expectedCharacterURL = "https://rickandmortyapi.com/api/character/%d".formatted(character.id());
            if (!expectedCharacterURL.equals(character.url()))
                System.err.printf("%s: Character-Url \"%s\" does not equal expected value \"%s\".%n", source, character.url(), expectedCharacterURL);

            return new RestCharacter(
                character.id(),
                character.name(),
                character.status(),
                character.species(),
                character.type(),   
                character.gender(),
                convert(character.origin  (), source+".origin"),
                convert(character.location(), source+".location"),
                character.image(),
                getIDs(character.episode(), "https://rickandmortyapi.com/api/episode/", "Episode", source+".episode"),
                character.created()
            );
        }
    };
    
    public interface GenericConverterInt<RestType, RAMType>
    {
        RestType convert(RAMType character, String source);
        Optional<RestType> convert(Optional<RAMType> value, String source);
        List<RestType> convert(List<RAMType> values, String source);
    }

    public static abstract class GenericConverter<RestType, RAMType> implements GenericConverterInt<RestType, RAMType>
    {
        @Override
        public abstract RestType convert(RAMType character, String source);

        @Override
        public List<RestType> convert(List<RAMType> values, String source)
        {
            if (values==null)
                return null;

            ArrayList<RestType> list = new ArrayList<>();
            int i=0;
            for (RAMType value : values) {
                list.add(convert(value, source+"["+i+"]"));
                i++;
            }
            return list;
        }

        @Override
        public Optional<RestType> convert(Optional<RAMType> value, String source)
        {
            return Optional.ofNullable(convert(value.orElse(null), source));
        }

        protected RestCharacter.Location convert(RAMCharacter.Location location, String source) {
            if (location==null)
                return null;

            return new RestCharacter.Location(
                location.name(),
                getID(location.url(), "https://rickandmortyapi.com/api/location/", "Location", source)
            );
        }

        protected Integer getID(String url, String urlPrefix, String idLabel, String source) {
            if (url==null || url.isEmpty()) {}
            else if (!url.startsWith(urlPrefix)) {
                System.err.printf("%s: Can't get %s Id from URL \"%s\". URL hasn't expected prefix \"%s\".%n", source, idLabel, url, urlPrefix);
            } else {
                String str = url.substring(urlPrefix.length());
                try {
                    return Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    System.err.printf("%s: Can't get %s Id from url \"%s\". Can't parse Id part \"%s\" of URL into Integer.%n", source, idLabel, url, str);
                }
            }
            return null;
        }

        protected List<Integer> getIDs(String[] urls, String urlPrefix, String idLabel, String source) {
            if (urls==null)
                return null;

            ArrayList<Integer> list = new ArrayList<>();
            for (int i=0; i<urls.length; i++) {
                Integer id = getID(urls[i], urlPrefix, idLabel, source+"["+i+"]");
                if (id!=null) list.add(id);
            }
            return list;
        }
    }
}
