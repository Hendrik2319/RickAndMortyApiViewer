import '../App.css';
import { Episode } from "../Types";
import EpisodeCard from "./EpisodeCard";
import createPage from './GenericPage';

export default function EpisodesPage() {
    return createPage<Episode>({
        apiPath: '/api/episode',
        pagePath: '/episodes',
        itemLabel_LC: 'episodes',
        createCard: ep => <EpisodeCard key={ep.id} episode={ep}/>,
    });
}