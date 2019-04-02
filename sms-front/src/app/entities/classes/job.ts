import { Location } from './location'

export class Job {
    id: Number;
    name: String;
    imageUrl: String;
    description: String;
    location: Location;
    requirements: String[];
    responsibilities: String[];
}
