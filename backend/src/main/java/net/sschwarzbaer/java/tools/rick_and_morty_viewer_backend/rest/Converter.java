package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api.RAMCharacter;

public class Converter {
    
    public static List<RestCharacter> convert(List<RAMCharacter> characters, String source) {
         if (characters==null)
            return null;

        ArrayList<RestCharacter> list = new ArrayList<>();
        int i=0;
        for (RAMCharacter character : characters) {
            list.add(convert(character, source+"["+i+"]"));
            i++;
        }
        return list;
    }

    public static Optional<RestCharacter> convert(Optional<RAMCharacter> character, String source) {
        return Optional.ofNullable(convert(character.orElse(null), source));
    }

    public static RestCharacter convert(RAMCharacter character, String source) {
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

    private static RestCharacter.Location convert(RAMCharacter.Location location, String source) {
         if (location==null)
            return null;

        return new RestCharacter.Location(
            location.name(),
            getID(location.url(), "https://rickandmortyapi.com/api/location/", "Location", source)
        );
    }

    private static Integer getID(String url, String urlPrefix, String idLabel, String source) {
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

    private static List<Integer> getIDs(String[] urls, String urlPrefix, String idLabel, String source) {
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
