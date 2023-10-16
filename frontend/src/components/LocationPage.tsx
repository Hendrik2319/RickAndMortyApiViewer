import '../App.css';
import { Location } from "../Types";
import LocationCard from "./LocationCard";
import GenericPage from './GenericPage';

export default function LocationPage() {
    return GenericPage<Location>({
        apiPath: '/api/location',
        itemLabel_LC: 'locations',
        createCard: loc => <LocationCard key={loc.id} location={loc}/>,
    });
}