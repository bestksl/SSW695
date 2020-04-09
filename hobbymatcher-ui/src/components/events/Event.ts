export interface Event {
    id: number;
    hobbyId: number;
    title: string;
    onDatetime: any; // string | Date;
    location: string;
    locationShort: string;
    locationObj: any; // only used in ui
    geoLat: number;
    geoLon: number;
    capacity: number;
    description: string;
    plus18Only: boolean;
    organizer: string;
    photoId: string;
    createdById: number;
}
