package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api;

public record RAMEpisode(
    int      id        , // id of the episode
    String   name      , // name of the episode
    String   air_date  , // air date of the episode
    String   episode   , // code of the episode
    String[] characters, // List of characters who have been seen in the episode
    String   url       , // Link to the episode's own URL endpoint
    String   created     // Time at which the episode was created in the database
) {
    
}
