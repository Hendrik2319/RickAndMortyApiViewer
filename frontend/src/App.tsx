import axios from 'axios';
import { useEffect, useState } from 'react';
import { Link, Route, Routes, useNavigate } from 'react-router-dom';
import './App.css';
import CharactersPage from './components/CharactersPage';
import EpisodesPage from './components/EpisodesPage';
import LocationPage from './components/LocationPage';

export default function App() {
	const [helloText, setHelloText] = useState<string>("");
    console.debug("Rendering App()");

    useEffect(loadHelloText, []);
    const navigate = useNavigate();

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
            <h1><Link to="/">Rick & Morty API - Viewer</Link></h1>
            <nav>
                <button onClick={()=>navigate("/characters/1")}>Characters</button>
                <button onClick={()=>navigate("/episodes/1"  )}>Episodes  </button>
                <button onClick={()=>navigate("/locations/1" )}>Locations </button>
            </nav>
            <Routes>
                <Route path='/'                 element={<CharactersPage/>}/>
                <Route path='/characters/:page' element={<CharactersPage/>}/>
                <Route path='/episodes/:page'   element={<EpisodesPage/>}/>
                <Route path='/locations/:page'  element={<LocationPage/>}/>
                <Route path='/hello'            element={<>Response: {helloText}</>}/>
            </Routes>
		</>
	)
}
