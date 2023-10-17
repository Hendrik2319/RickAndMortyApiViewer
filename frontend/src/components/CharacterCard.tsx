import '../App.css';
import { Character } from "../Types";
import ValueOutputField from "./ValueOutputField";

type Props = {
    character: Character
};

export default function CharacterCard( props:Props ) {
    
    return (
        <div className="ItemCard">
            {props.character.id      && <ValueOutputField name="ID"      value={props.character.id     }/>}
            {props.character.name    && <ValueOutputField name="Name"    value={props.character.name   }/>}
            {props.character.status  && <ValueOutputField name="Status"  value={props.character.status }/>}
            {props.character.species && <ValueOutputField name="Species" value={props.character.species}/>}
            {props.character.type    && <ValueOutputField name="Type"    value={props.character.type   }/>}
            {props.character.gender  && <ValueOutputField name="Gender"  value={props.character.gender }/>}
            {props.character.image   && <img className="CharacterImage" src={props.character.image}/>}
        </div>
    )
}

/* 
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
 */