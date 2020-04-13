export interface Participant {
  id: number; // userId
  firstName: string;
  nickName: string;
  lastName: string;
  email: string;
  gender: string;
  photoId: string;
  requestedOnDatetime: any; // string | Date
  approvedOnDatetime: any; // string | Date
  participatedOnDatetime: any; // string | Date
}