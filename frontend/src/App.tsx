import { useEffect, useState } from 'react';
import './App.css'
import axios from 'axios';
import { Route, Routes, useNavigate, Link } from 'react-router-dom';
import CharactersPage from './components/CharactersPage';

export default function App() {
	const [helloText, setHelloText] = useState<string>("");

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
                <button onClick={()=>navigate("/characters")}>Characters</button>
                <button onClick={()=>navigate("/episodes"  )}>Episodes  </button>
                <button onClick={()=>navigate("/locations" )}>Locations </button>
            </nav>
            <Routes>
                <Route path='/' element={
                    <CharactersPage/>
                }/>
                <Route path='/characters' element={
                    <CharactersPage/>
                }/>
                <Route path='/hello' element={
                    <>
            			Response: {helloText}
                    </>
                }/>
            </Routes>
		</>
	)
}
