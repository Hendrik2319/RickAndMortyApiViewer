import axios from "axios";
import { useEffect, useState } from "react";

type Props = {

}

export default function CharactersPage( props:Props ) {
    const [page, setPage] = useState<number>(1);
    const [content, setContent] = useState();

    useEffect( loadPage, [] );

    function loadPage() {
        axios.get("/api/character?page="+page)
            .then((response) => {
                if (response.status!==200)
                    throw "Get wrong response status, when loading characters page "+page+": "+response.status;
                setContent(response.data);
            })
            .catch((error)=>{
                console.error(error);
            })
    }

    return (
        <>
        </>
    )
}