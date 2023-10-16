
export type Character = {
    id      : number  , // id of the character
    name    : string  , // name of the character
    status  : Status  , // status of the character ('Alive', 'Dead' or 'unknown')
    species : string  , // species of the character
    type    : string  , // type or subspecies of the character
    gender  : Gender  , // gender of the character ('Female', 'Male', 'Genderless' or 'unknown')
    origin  : LocationInfo, // Name and link to the character's origin location
    location: LocationInfo, // Name and link to the character's last known location endpoint
    image   : string  , // Link to the character's image. All images are 300x300px and most are medium shots or portraits since they are intended to be used as avatars
    episodes: number[], // List of episodes in which this character appeared
    created : string  ,  // Time at which the character was created in the database
};

export type Status = "Alive" | "Dead" | "unknown";
export type Gender = "Female" | "Male" | "Genderless" | "unknown";
export type LocationInfo = {
    name: string,
    locationId: number | null,
}

export type Episode = {
    id         : number   // id of the episode
    name       : string   // name of the episode
    air_date   : string   // air date of the episode
    episode    : string   // code of the episode
    characters : number[] // List of characters who have been seen in the episode
    created    : string   // Time at which the episode was created in the database
}

export type Location = {
    id        : number   // id of the location
    name      : string   // name of the location
    type      : string   // type of the location
    dimension : string   // dimension in which the location is located
    residents : number[] // List of character who have been last seen in the location
    created   : string   // Time at which the location was created in the database
}
