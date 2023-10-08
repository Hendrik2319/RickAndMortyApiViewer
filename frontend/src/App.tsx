import { useEffect, useState } from 'react';
import './App.css'
import axios from 'axios';

export default function App() {
	const [helloText, setHelloText] = useState<string>("");

    useEffect(loadHelloText, []);

    function loadHelloText (){
        axios.get("/api/hello")
            .then((response) => {
                if (response.status!==200)
                    throw "Get wrong response status, when loading Hello text: "+response.status;
				setHelloText(response.data);
            })
            .catch((error)=>{
                console.error(error);
            })
    }

	return (
		<>
			<h1>Rick & Morty API - Viewer</h1>
			Response: {helloText}
		</>
	)
}
