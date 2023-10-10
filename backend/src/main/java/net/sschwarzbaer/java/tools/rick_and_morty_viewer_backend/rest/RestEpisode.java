package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.rest;

import java.util.List;

public record RestEpisode (
    int           id        , // id of the episode
    String        name      , // name of the episode
    String        air_date  , // air date of the episode
    String        episode   , // code of the episode
    List<Integer> characters, // List of characters who have been seen in the episode
    String        created     // Time at which the episode was created in the database
) {
    
}
