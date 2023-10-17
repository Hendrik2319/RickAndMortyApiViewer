import '../App.css';
import { Character } from "../Types";
import CharacterCard from "./CharacterCard";
import createPage from './GenericPage';

export default function CharactersPage() {
    return createPage<Character>({
        apiPath: '/api/character',
        pagePath: '/characters',
        itemLabel_LC: 'characters',
        createCard: ep => <CharacterCard key={ep.id} character={ep}/>,
    });
}