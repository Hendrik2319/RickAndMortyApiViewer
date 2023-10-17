import '../App.css'
import { Episode } from "../Types";
import ValueOutputField from "./ValueOutputField";

type Props = {
    episode: Episode
};

export default function EpisodeCard( props:Props ) {
    
    return (
        <div className="ItemCard">
            {props.episode.episode    && <ValueOutputField name="Episode"    value={props.episode.episode }/>}
            {props.episode.id         && <ValueOutputField name="ID"         value={props.episode.id      }/>}
            {props.episode.name       && <ValueOutputField name="Name"       value={props.episode.name    }/>}
            {props.episode.air_date   && <ValueOutputField name="Air Date"   value={props.episode.air_date}/>}
            {props.episode.characters && <ValueOutputField name="Characters" value={props.episode.characters.length + " Characters"}/>}
        </div>
    )
}

/*
export type Episode = {
    id         : number   // id of the episode
    name       : string   // name of the episode
    air_date   : string   // air date of the episode
    episode    : string   // code of the episode
    characters : number[] // List of characters who have been seen in the episode
    created    : string   // Time at which the episode was created in the database
}
 */