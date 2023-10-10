import { Character } from "../Types";

type Props = {
    character: Character
};

export default function CharacterCard( props:Props ) {
    
    return (
        <div className="CharacterCard">
            <div>id      : {props.character.id     }</div>
            <div>name    : {props.character.name   }</div>
            <div>species : {props.character.species}</div>
            <div>type    : {props.character.type   }</div>
            <div>gender  : {props.character.gender }</div>
            {props.character.image && <img className="CharacterImage" src={props.character.image}/>}
        </div>
    )
}