import axios from "axios";
import { useEffect, useState } from "react";
import '../App.css';


export type Props<Type> = {
    apiPath: string // /api/location
    itemLabel_LC: string // locations
    createCard: (item:Type) => JSX.Element // ch => <LocationCard key={ch.id} location={ch}/>
};

export default function GenericPage<Type>( props: Props<Type>) {
    const [page, setPage] = useState<number>(1);
    const [locations, setLocations] = useState<Type[]>([]);

    useEffect( loadPage, [ page ] );

    function loadPage() {
        axios.get(props.apiPath+"?page="+page)
            .then((response) => {
                if (response.status!==200)
                    throw "Get wrong response status, when loading "+props.itemLabel_LC+" page "+page+": "+response.status;
                    setLocations(response.data);
            })
            .catch((error)=>{
                console.error(error);
            })
    }

    function switchPage( pageInc: number): void {
        if (page+pageInc > 0)
            setPage(page+pageInc);
    }

    return (
        <>
            <button onClick={()=>switchPage(-1)}>&lt;</button>
            {page}
            <button onClick={()=>switchPage(+1)}>&gt;</button>
            <div className="ItemsList">
                {locations.map( props.createCard )}
            </div>
        </>
    )
}