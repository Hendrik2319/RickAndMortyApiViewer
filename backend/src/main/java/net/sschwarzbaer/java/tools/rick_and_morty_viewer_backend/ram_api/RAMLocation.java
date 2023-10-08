package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api;

public record RAMLocation(
    int      id       , // id of the location
    String   name     , // name of the location
    String   type     , // type of the location
    String   dimension, // dimension in which the location is located
    String[] residents, // List of character who have been last seen in the location
    String   url      , // Link to the location's own URL endpoint
    String   created    // Time at which the location was created in the database
) {
    
}
