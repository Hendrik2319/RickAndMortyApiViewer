import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import '../App.css';


export type Props<Type> = {
    apiPath: string
    itemLabel_LC: string
    createCard: (item:Type) => JSX.Element
};

export default function GenericPage<Type>( props: Props<Type>) {
    const [page, setPage] = useState<number>(1);
    const [locations, setLocations] = useState<Type[]>([]);
    const { page: initialPage } = useParams();

    useEffect( loadPage, [ page ] );
    useEffect( setInitialPage, [ initialPage ] );

    function setInitialPage() {
        if (initialPage)
            setPage( parseInt( initialPage ));
    }

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