export interface PairType{
    user: PairMemberType
    pairMsg: string,
    pairStatus: 0 | 1 | 2 | 3
    memberList?: PairMemberType[]
}
export interface PairMemberType{
    userNm: string;
    userSn: number;
    thurmbnailUrl: string;
}
export interface PagenationInfo{
    page: number,
    count: number,
    total: number,
}