import '../App.css';
import { Episode } from "../Types";
import EpisodeCard from "./EpisodeCard";
import GenericPage from './GenericPage';

export default function EpisodesPage() {
    return GenericPage<Episode>({
        apiPath: '/api/episode',
        itemLabel_LC: 'episodes',
        createCard: ep => <EpisodeCard key={ep.id} episode={ep}/>,
    });
}