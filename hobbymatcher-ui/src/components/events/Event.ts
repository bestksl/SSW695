export interface Event {
    id: number;
    hobbyId: number;
    title: string;
    onDatetime: any; // string | Date;
    location: string;
    capacity: number;
    description: string;
    plus18Only: boolean;
    organizer: string;
    photoId: string;
}
