export interface Blog {
    id: number;
    title: string;
    html: string;
    onDatetime: any; // string | Date;
    clapCount: number;
    byUserId: number;
    byUserFirst: string;
    byUserLast: string;
    byUser: any;
    forEventId: number;
}
