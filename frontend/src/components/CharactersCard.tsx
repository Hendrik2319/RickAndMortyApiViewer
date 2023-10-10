import { Character } from "../Types";
import ValueOutputField from "./ValueOutputField";

type Props = {
    character: Character
};

export default function CharacterCard( props:Props ) {
    
    return (
        <div className="CharacterCard">
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