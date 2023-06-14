export interface GroupType{
    user: GroupMemberType
    groupMsg: string,
    groupStatus: 0 | 1 | 2 | 3
    memberList?: GroupMemberType[]
}
export interface GroupMemberType{
    userNm: string;
    userSn: number;
    thurmbnailUrl: string;
}
export interface PagenationInfo{
    page: number,
    count: number,
    total: number,
}