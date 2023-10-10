import '../App.css'
import axios from "axios";
import { useEffect, useState } from "react";
import { Episode } from "../Types";
import EpisodeCard from "./EpisodeCard";

export default function EpisodesPage() {
    const [page, setPage] = useState<number>(1);
    const [episodes, setEpisodes] = useState<Episode[]>([]);

    useEffect( loadPage, [ page ] );

    function loadPage() {
        axios.get("/api/episode?page="+page)
            .then((response) => {
                if (response.status!==200)
                    throw "Get wrong response status, when loading episodes page "+page+": "+response.status;
                    setEpisodes(response.data);
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
                {episodes.map( ch => <EpisodeCard key={ch.id} episode={ch}/> )}
            </div>
        </>
    )
}