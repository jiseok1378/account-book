<template>
  <main-viewer
    :title="`그룹 목록`"
    :subTitle="`여기에서는 내가 가입한 그룹의 리스트를 확인할 수 있어요`"
  >
    <group-tab
      class="group_list_tab"
      :items="tabs"
      :value="tab"
      labelKey="label"
      @change="changeTab"
    />
    <div>
        <v-row v-for="(list, index1) in make2Dimension(itemList[tab],colSize)" :key="index1">
            <v-col 
                :cols="12"
                :lg="3" 
                :md="6"
                :sm="12"
                v-for="(item, index2) in list" :key="index2">
                <group-list-item :item="item" @click="clickGroupItem(item)"/>
            </v-col>
        </v-row>
        <infinite-loading :identifier="tab" @infinite="loadMore" />
    </div>
  </main-viewer>
</template>

<script lang="ts">
import { GroupListViewType } from "@/@types/global-types";
import MainViewer from "@/components/global/MainViewer.vue";
import GroupTab from "@/components/group/GroupTab.vue";
import GroupListItem from "@/components/group/list/GroupListItem.vue";
import Vue from "vue";

const loadCount = 16;
const thumbnail = [
  'https://velog.velcdn.com/images/ubin_ing/post/50f59a40-a093-4a8a-ba54-8fb3cddd7cac/image.png', 
  'https://velog.velcdn.com/images/sukyoungshin/post/89cbbb38-4e4b-45cf-aa3f-9dfe06ff7a91/image.webp',
  'https://velog.velcdn.com/images/user2zzzz/post/192b16a7-527e-44fe-8e0c-515951c1aa34/image.gif',
  'https://velog.velcdn.com/images/eunminbb/post/38401500-c0f4-4044-b1c5-3ead34600989/image.png',
]
function getRandomInt(min, max) { //min ~ max 사이의 임의의 정수 반환
  return Math.floor(Math.random() * (max - min)) + min;
}
const sleep = (time: number) => new Promise<void>((resolve, reject)=>{
  setTimeout(()=>{resolve()}, time)
})
export default Vue.extend({
  components: { MainViewer, GroupTab, GroupListItem },
  data() : GroupListViewType {
    return {
      tab: "made",
      colSize: 4,
      pagenationInfo: {
        made:{
          page: 1,
          loadCount: loadCount,
          total: 200,
        },
        invited:{
          page: 1,
          loadCount: loadCount,
          total: 20,
        },
      },
      isLoad: false,
      itemList: {
        made: [],
        invited: [],
      },
      tabs: [
        {
          label: "내가 만든 그룹",
          id: 1,
          value: "made",
          total: 0,
        },
        {
          label: "내가 들어간 그룹",
          id: 2,
          value: "invited",
          total: 0,
        },
      ],
    };
  },
  methods: {
    clickGroupItem(item){
      this.$EventBus.$emit('changeRoute', {path: `/group/detail/${item.groupSn}`})
    },
    getPagingMethod(self : GroupListViewType){
      return {
        isMore:{
          invited(){ return self.pagenationInfo.invited.total > self.itemList.invited.length },
          made(){ return self.pagenationInfo.made.total > self.itemList.made.length } 
        },
        incresePage:{
          invited(){ return self.pagenationInfo.invited.page++ },
          made(){ return self.pagenationInfo.made.page++ }   
        },
        /* TODO: 서버와 연결시 리펙토링 */          
        loadItem:{
          async invited(){
            for(let i = 0; i < self.pagenationInfo.invited.loadCount ; i++){
              self.itemList.invited.push({
                groupSn: i,
                groupIntro: '그룹 소개',
                groupNm: "그룹",
                role: '주인장',
                groupMemCnt: 10,
                groupThumbnail: thumbnail[getRandomInt(0, 4)]
              });
            }
          },
          async made(){
            for(let i = 0; i < self.pagenationInfo.made.loadCount; i++){
              self.itemList.made.push({
                groupSn: i,
                groupIntro: '그룹 소개',
                groupNm: "그룹",
                role: '주인장',
                groupMemCnt: 10,
                groupThumbnail: thumbnail[getRandomInt(0, 4)]
              });
            }
          }
        }
      }
    },
    changeTab(value: number) {
      this.tab = this.tabs[value].value
    },
    /* 1차원 배열을 받아 2차원 배열을 만드는 함수 */
    make2Dimension(array : any[], _2DimLen : number) : any[][] {
        const loop = (nextArray: any[], newArray : any[][] = []) : any[][]=> {
            if(nextArray.length <= 0){
                return newArray;
            }
            else{
                const _ = nextArray.filter((x,i) => i < _2DimLen)
                newArray.push(_)
                return loop(nextArray.filter(x => !_.includes(x)), [...newArray])
            }
        }
        return loop(array)
    },
    async loadMore(e) {
      const pagingMethod = this.getPagingMethod(this);
      await sleep(1000); /* 서버와 연결시 삭제 */
      console.log(this.tab)
      if(await pagingMethod.isMore[this.tab]()){
        await pagingMethod.loadItem[this.tab]();
        e.loaded();
      }
      else{
        e.complete();
      }
    },
  },
});
</script>

<style lang="scss" scoped>
.group_list_tab{
  margin-bottom: 30px;
}
</style>
