export interface Comment {
    id: number;
    ownerType: string;
    ownerId: number;
    parentId: number;
    byUserId: number;
    byUserFirst: string;
    byUserLast: string;
    byUserPhotoId: string;
    content: string;
    onDatetime: any; // string | Date;
}
