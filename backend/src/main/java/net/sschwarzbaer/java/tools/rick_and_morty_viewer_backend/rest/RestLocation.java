package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.rest;

import java.util.List;

public record RestLocation(
    int           id       , // id of the location
    String        name     , // name of the location
    String        type     , // type of the location
    String        dimension, // dimension in which the location is located
    List<Integer> residents, // List of character who have been last seen in the location
    String        created    // Time at which the location was created in the database
) {
}
