export interface Filter {
    searchPhrase: string;
    searchScope: string;
    sortBy: string;
    feeStart: number;
    feeEnd: number;
    categories: number[];
    offset: number;
    perpage: number;
    count: number;
}
