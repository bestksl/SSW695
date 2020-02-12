export interface Review {
    id: number;
    userPhoto: string;
    userName: string;
    datetime: Date;
    rating: number;
    reviewText: string;
    positiveVoteCount: number;
    reviews: Review[];
    reviewsCount: number;
}
