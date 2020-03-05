export interface Event {
    id: number;
    title: string;
    datetime: Date;
    location: string;
    capacity: number;
    description: string;
    plus18: boolean;
    fee: number;
    organizer: string;
    coverPhotoId: string;
}
