import '../App.css'
import axios from "axios";
import { useEffect, useState } from "react";
import { Location } from "../Types";
import LocationCard from "./LocationCard";

export default function LocationPage() {
    const [page, setPage] = useState<number>(1);
    const [locations, setLocations] = useState<Location[]>([]);

    useEffect( loadPage, [ page ] );

    function loadPage() {
        axios.get("/api/location?page="+page)
            .then((response) => {
                if (response.status!==200)
                    throw "Get wrong response status, when loading locations page "+page+": "+response.status;
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
                {locations.map( ch => <LocationCard key={ch.id} location={ch}/> )}
            </div>
        </>
    )
}