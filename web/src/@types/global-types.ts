export interface GroupRiItem {
  user: GroupMemberType;
  groupMsg: string;
  groupStatus: 0 | 1 | 2 | 3;
  memberList?: GroupMemberType[];
}
export interface GroupMemberType {
  userNm: string;
  userSn: number;
  thurmbnailUrl: string;
}
export interface PagenationInfo {
  page: number;
  loadCount: number;
  total: number;
}
export interface GroupTabs {
  id: number;
  label: string;
  value: string;
  total: number;
}

export interface GroupRiViewType {
  tab: string;
  pagenationInfo: {
    send: PagenationInfo;
    post: PagenationInfo;
  };
  tabs: GroupTabs[];
  isLoad: boolean;
  itemList:{
    send: GroupRiItem[];
    post: GroupRiItem[];
  }
}
export interface GroupItem{
  groupNm: string
  groupIntro: string
  role: string
  groupThumbnail: string
  groupMemCnt: number
}

export interface GroupListViewType{
  tab: string
  pagenationInfo: {
    made: PagenationInfo;
    invited: PagenationInfo;
  };
  itemList:{
    made: GroupItem[]
    invited:  GroupItem[]
  }
  colSize: 4
  isLoad: boolean;
  tabs: GroupTabs[];
}