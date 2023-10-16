import { Location } from "../Types";
import ValueOutputField from "./ValueOutputField";

type Props = {
    location: Location
};

export default function LocationCard( props: Props ) {
    
    return (
        <div className="ItemCard">
            {props.location.id        && <ValueOutputField name="ID"        value={props.location.id       }/>}
            {props.location.name      && <ValueOutputField name="Name"      value={props.location.name     }/>}
            {props.location.type      && <ValueOutputField name="Type"      value={props.location.type     }/>}
            {props.location.dimension && <ValueOutputField name="dimension" value={props.location.dimension}/>}
        </div>
    )
}

/*
 export type Location = {
    id        : number   // id of the location
    name      : string   // name of the location
    type      : string   // type of the location
    dimension : string   // dimension in which the location is located
    residents : number[] // List of character who have been last seen in the location
    created   : string   // Time at which the location was created in the database
}
 */