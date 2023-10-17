import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import '../App.css';

export type Props<Type> = {
    apiPath: string
    pagePath: string
    itemLabel_LC: string
    createCard: (item:Type) => JSX.Element
};

export default function createPage<Type>( props: Props<Type> ) {
    const [data, setData] = useState<Type[]>([]);
    const { page: pageIn } = useParams();
    const navigate = useNavigate();
    console.debug("Rendering GenericPage<"+props.itemLabel_LC+">( page:\""+pageIn+"\" )");

    let page = 1;
    if (pageIn)
        page = parseInt(pageIn);

    useEffect( loadPage, [ page ] );

    function loadPage() {
        axios.get(props.apiPath+"?page="+page)
            .then((response) => {
                if (response.status!==200)
                    throw "Get wrong response status, when loading "+props.itemLabel_LC+" page "+page+": "+response.status;
                    setData(response.data);
            })
            .catch((error)=>{
                console.error(error);
            })
    }

    function switchPage( pageInc: number): void {
        //console.debug("switchPage( "+pageInc+" ) -> "+(page+pageInc))
        if (page+pageInc > 0) {
            const path = props.pagePath + "/" + (page + pageInc);
            //console.debug("navigate( \""+path+"\" )")
            navigate(path);
        }
    }

    return (
        <>
            <button onClick={()=>switchPage(-1)}>&lt;</button>
            {page}
            <button onClick={()=>switchPage(+1)}>&gt;</button>
            <div className="ItemsList">
                {data.map( props.createCard )}
            </div>
        </>
    )
}