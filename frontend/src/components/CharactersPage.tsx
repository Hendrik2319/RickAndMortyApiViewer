import axios from "axios";
import { useEffect, useState } from "react";
import { Character } from "../Types";
import CharacterCard from "./CharactersCard";

/*
type Props = {

}
 */
export default function CharactersPage( /* props:Props */ ) {
    const [page, setPage] = useState<number>(1);
    const [characters, setCharacters] = useState<Character[]>([]);

    useEffect( loadPage, [ page ] );

    function loadPage() {
        axios.get("/api/character?page="+page)
            .then((response) => {
                if (response.status!==200)
                    throw "Get wrong response status, when loading characters page "+page+": "+response.status;
                    setCharacters(response.data);
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
            <div className="CharactersList">
                {characters.map( ch => <CharacterCard key={ch.id} character={ch}/> )}
            </div>
        </>
    )
}