import '../App.css';
import { Location } from "../Types";
import GenericPage from './GenericPage';
import LocationCard from "./LocationCard";

export default function LocationPage() {
    return GenericPage<Location>({
        apiPath: '/api/location',
        itemLabel_LC: 'locations',
        createCard: loc => <LocationCard key={loc.id} location={loc}/>,
    });
}