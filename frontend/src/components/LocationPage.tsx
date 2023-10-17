import '../App.css';
import { Location } from "../Types";
import createPage from './GenericPage';
import LocationCard from "./LocationCard";

export default function LocationPage() {
    return createPage<Location>({
        apiPath: '/api/location',
        pagePath: '/locations',
        itemLabel_LC: 'locations',
        createCard: loc => <LocationCard key={loc.id} location={loc}/>,
    });
}