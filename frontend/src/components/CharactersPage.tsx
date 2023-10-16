import '../App.css';
import { Character } from "../Types";
import CharacterCard from "./CharacterCard";
import GenericPage from './GenericPage';

export default function CharactersPage() {
    return GenericPage<Character>({
        apiPath: '/api/character',
        itemLabel_LC: 'characters',
        createCard: ep => <CharacterCard key={ep.id} character={ep}/>,
    });
}