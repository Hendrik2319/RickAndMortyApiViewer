package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.rest;

import java.util.List;

import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api.RAMCharacter.Gender;
import net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api.RAMCharacter.Status;

public record RestCharacter(
    int           id      , // id of the character
    String        name    , // name of the character
    Status        status  , // status of the character ('Alive', 'Dead' or 'unknown')
    String        species , // species of the character
    String        type    , // type or subspecies of the character
    Gender        gender  , // gender of the character ('Female', 'Male', 'Genderless' or 'unknown')
    Location      origin  , // Name and link to the character's origin location
    Location      location, // Name and link to the character's last known location endpoint
    String        image   , // Link to the character's image. All images are 300x300px and most are medium shots or portraits since they are intended to be used as avatars
    List<Integer> episodes, // List of episodes in which this character appeared
    String        created   // Time at which the character was created in the database
) {
    public record Location(String name, Integer locationId) {}
}
