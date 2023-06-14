<template>
  <main-viewer
    :title="`그룹 목록`"
    :subTitle="`여기에서는 내가 가입한 그룹의 리스트를 확인할 수 있어요`"
  >
    <group-tab
      :items="tabs"
      :value="tab"
      labelKey="label"
      @change="changeTab"
      :selectItems="['a', 'b']"
    />
    <div>
        <v-row v-for="(list, index1) in make2Dimension(test,colSize)" :key="index1">
            <v-col 
                :cols="12"
                :lg="3" 
                :md="6"
                :sm="12"
                v-for="(item, index2) in list" :key="index2">
                <group-list-item />
            </v-col>
        </v-row>
    </div>
  </main-viewer>
</template>

<script lang="ts">
import { GroupTabsType } from "@/@types/global-types";
import MainViewer from "@/components/global/MainViewer.vue";
import GroupTab from "@/components/group/GroupTab.vue";
import GroupListItem from "@/components/group/list/GroupListItem.vue";
import Vue from "vue";
export default Vue.extend({
  components: { MainViewer, GroupTab, GroupListItem },
  data() {
    return {
      tab: 1,
      colSize: 4,
      test: [],
      tabs: [
        {
          label: "내가 만든 그룹",
          id: 1,
          value: "send",
          total: 0,
        },
        {
          label: "내가 들어간 그룹",
          id: 2,
          value: "post",
          total: 0,
        },
      ],
    };
  },
  beforeMount(){
    (this.test as any[]) = [1,2,3,4,5,6,7,8, 9,10] as any[]
    console.log()
  },
  methods: {
    changeTab(value: GroupTabsType) {
      this.tab = value.id;
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
    }
  },
});
</script>

<style></style>
