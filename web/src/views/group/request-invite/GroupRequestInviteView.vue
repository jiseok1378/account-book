<template>
  <main-viewer :title="`그룹 요청/초대 목록`" subTitle="여기에서는 내가 보낸 그룹 요청, 받은 그룹 요청, 상태를 확인할 수 있어요.">
    <group-ri-tab  
      :items="tabs" 
      :value="tab" 
      labelKey="label"
      @change="changeTab" 
      :selectItems="['a', 'b']"
    /> 
    <div>
      <group-ri-item 
        v-for="(item, index) in (tab == 1? sendList : postList)"
        :key="index" 
        :item="item" 
      />
      <infinite-loading :identifier="tab" @infinite="loadMore"></infinite-loading>
    </div>

  </main-viewer>
</template>

<script lang="ts">
import { GroupTabsType, GroupViewType } from '@/@types/global-types';
import MainViewer from '@/components/global/MainViewer.vue';
import GroupRiTab from '@/components/group/GroupTab.vue';
import GroupRiItem from '@/components/group/request-invite/GroupRiItem.vue';
import Vue from 'vue';


function getRandomInt(min, max) { //min ~ max 사이의 임의의 정수 반환
  return Math.floor(Math.random() * (max - min)) + min;
}
const sleep = (time: number) => new Promise<void>((resolve, reject)=>{
  setTimeout(()=>{resolve()}, time)
})
    
const imgList = [
  'https://image.kmib.co.kr/online_image/2020/0122/202001220402_11170924119205_1.jpg',
  'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBw8QDw8NEA8NDxAPDg8PDw4PDw8NDQ4PFREWFhYRFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OFxAQGCsfIB0tLS0vLS0tKystLS0rLSsrLS0tKy0rLS0tKystLS0tLS0tLS0tLS0tLS0tLS0tLS0tLf/AABEIAQUAwQMBIgACEQEDEQH/xAAcAAEAAQUBAQAAAAAAAAAAAAAAAQIDBQYHBAj/xABAEAACAQICBgcECAUDBQAAAAAAAQIDEQQhBQYSMUFRBxMiYXGBkTJSocEUI0JicoKx8DOSssLRJFOiFSU0c3T/xAAYAQEBAQEBAAAAAAAAAAAAAAAAAQMCBP/EACERAQADAAICAwADAAAAAAAAAAABAhEDIRIxQVFxIkJh/9oADAMBAAIRAxEAPwDuAAAAAASQAJIAAkEEgAAAAAAEACQCAJAIAEgAAAAAAEAAAAAAJIAAAACJzUU5SaikruTaSS5tmmad6RcNQuqKdaXCfs0vLjLyy7wsRrdHJJXdklvbyR53j6Of1tLJXdpxdl6nCtYtdq2MsqkpKEd0IScKbfNpb/M12OkZbTtKSXJbvMGPonF6yYOllPEUk/d2ry9C3Q1rwM3srE0s9z2lY+eI1dt+278b3bInWq03eM2/KxJ11ERL6hhNSSkmmnuad0yT581X14xODms9qm2tuk29hr5PvR23VzWHD46kqtGSurbdNvt033rl3iJ1LVxlgAVyAEgAABAJAEAkAAAAAAAAACCTVOkfTrwmDcYO1XEXpwd7OMbdufkv1RJnIWI2caRr9rZLFVpYWlJrDUpNNRedeadnJ/cXDh8tHxNdttQSbW+cs0u6PI8ssTa/K+7dd9/+CZKWzyvwXgSP9aT9Q8ldWeeb5syur+HVRzpyitmcbXa9lrNO5i3Tu+bfmzbdVNCVNpVbTy3O3wJNk8ZariMO6dRpxyvlbdY9N9qGzb8r+R0PS+rkasW5RSbXBWzvvNOx+reIpK8Gmt+zm8iReF8Jhrst9nk1uZlNX9N1sJWjVpTcZRfDivda3NPkYzERnF9teDLUlxRV9PpnVLWSlj6CqRtGpGyq07+zLmvuszh866i6xSwmJp1U3a6jUXCdNtbUfmu9I+hqNWM4xnF3jOKlF84tXTLE/Di1c7hcAB04AAAAAAAAAAAAAAEACTinTNpG+MVG+VKlGNuTl2pPxd4+h2pnzHrjpX6XjsTiE7xlWl1b4OnF7MX/ACpHNndWNoNXUnw3ckke+pUukkrym92/eYtVLZfvwNu1I0G8RU66SexF2X3nxf75nMzkO6xrLanaoqbVask080u/mdGpYKMIpRikkuBdwWHUYpLkeqSyOc32sz9MXWpX3mPxmFTW4y1ZHlrLIzmGkS59rLoWLjJ2/fM5/FOMnCXC68zsmlYXizlesWF2Km2tzdjrjn4OSOteOh2Z24P93O+dFWmHiMF1E3ephZdW+bpvOEv1X5Tgsc1GffZ+PH5HSeh/GOONnTvlWotNcHKFmn6ZeprHtjMdOygEHbJIAAAAAAAAIJAAgkACABj9Yarhg8XON9qOGrONufVux8t1sm1+7H1JrH/4eK/+asvLYZ8w6QhaUrcZZeBzPt3X0t6Pw0qtSNOO+TS9Wdz0BgIYejCCSSjHf8znXRxo5SqSqtew0l4nVqcFa1kYWnZ/G8Rlf1XR0pTu0tqVsm0skWK2s2FUurcpKV7PsvJ95GJ0lQw0HOpOnSjzdkarU1v0bVnswqwcm7ZwaT7rtHXlOJ4xMtvp4yFRXhJSKKzPHoiUJS7Nr24bmijWLE9TG/PI53rXWd4x2lcZRjfanFd18zQdZq9GpGWzK/LJ2NmxujcKoPEYqrGnF+/U2Fd7kub7jB4yWj5xcKHVzduF7vvVyR127mN6avovtNw95Xj+JcP1N76Lo/8AcaNslaT9ISVvRo0DDS6urdZbMrruVzrXRlo5fTZVl7KpbcbcJSsreFnL0Nd/l+sP679OsAgGrBIAAAAACAAJAAAAAAALWJpKcJwaupRlFrmmrWPnrF6C2arotOdRStJL2Ypb8+Z9FGrawaGj1nXQUYyldqVsttvO/c9/mzPk3NhtwzG5LUtTNFuhGpFq3b+SNtjTyLOEo2TTttb3bdex6oGMQ1tLAaR1bp1Kka05TnKDvDa7cYvmo7vmaetSo0aspwlWq3h1cVUTahDK0VfgkklyR1eEbpnjlFJ5nWZHSRbZ7jWJ1Y0bKjG8t7SWbu0keDX6LcIW95G205JrI1/XCjtUr+60zm0ZC1tttlqWltCLFR2nUqQewoLZ3RhbtQtxUt7vvsuRgMVq1sRgoTk3T3PZtxuzetAVFUpLuy9C/j6Cs3YeU47yN9OQ47DyhUTlzzysmdu6I9l4NyvdqTinxUeCf74mjU9GQrV5bcbwhRxMpdyWHqWfk7Hs6JdNvDVXhqt1Tqq8ZWdk7qN2+V8r83biraUncljyRmw7SADZ5wAAAAAAAAAACCQBAJAEFjHUHUg4rfdNX3XPQBPaxOMF/wBPnBbcmuVlnlzbPPubRsOIhtRlHu+Jrst7MbVxrW0z7XY1LHgxlV8N7L8mWKbTlmcy0joeKlThH6qrVbkk1T2LpP7T2msvDM8OsWOpqlPaaitlb836czM1q0IxzlFeLSNR0xGhOMtuULuV1msyWhaRs7jF6v4yKqzULuErO9rZ8TN4+rkzB4Zwpu8bW3XW4zOAiqlRbWa3nER8NLTnazVwToYOtXeU6tNxXO82lFLvb/eZk8Nq9Sw+jsPCpBOtGNSa2c57VSFuri99nKUTKYqlGcsPGVtlXxE1vtCPZpq3fJ3X4TNYTBOc41qits/wqXu8pPvzeXC5vWuPNa2vdhIONOnGWco04KT71FJl0kGrEAAAAAAABAJAEEgAQCQBAJIlJJNtpJK7bySXMDFax6fo4Cj19baaclGMIbO3J2byu1uSbMK9IwqWqLJTW0k+TzXnY5L0jazfT8ZKcZN4ei+rw64OK9qp4yefhs8joWh4RnQg07xcVstcU0mmvUx5bTGNuKIZvburnklg4zd5Z2Vlm1bvPL1s6btLNcJf5MlhqiksjKJ1rMYwekND00m9mbb4xqTi/wBbGq4vRTeSUk+DlUmkl6nS6sFZ3Nc0oktyLZtTmtmNTo6OUJxleU2vtNt+SvwN31ZwbqzSV7fafuxRjdC6LliJ2Sdk/idEwujFSo9TTtFyVpTWUs97XfbcdUrM9vPy3+Hk0Th1UqVMS0tjb2aa+y4U+zC3NWvLxm+RmymlTUYxhFJRilFJbkluRWbxGPPM6gEgqAAAgkACCQAIBIAgEgCAAAOYdLuuKpQloyhL6yol9JmvsU2r9V4y492XE27XPWilo+g5txlXmn1NJ8X70vur47vD5x0njZ1qlSrOTlOpOU5ye+UpO7YV5ZzOi9GOssbLR9ZpSu3h5PdJcab71w7suBzZspU2mmm000007NNbmnzObV8oxa2yX0fWpJox7ozpu8H+V7jBaha3LGU/o9ZpYqnHN7uvgvtrv5rz8NpmeW1cl6a21isVpiccnG3fvRiK2kOtko82ZvHUk08jB4fDJVU7cTnvWkZjfNV69ClNYJWVZ4dYi/GcHNxfo0vU2U4FrNp+phtM0sRTfaw9GjG18pRe1KUH3NTa8zt+h9LUMXRhXozjKM4xk4qSc4Nr2ZJbmj219PFb29wAK5ASQBIAAAAAAAIAAAkgickk5NpJK7bdklzbAk1fW3XbDYBOC+uxFsqUN0Hwc3w8N5rmvHSNGmpYfByvLNSrLh+H/JyLFY2c25Sk2222297YV69YNMVsVWnXrSbnJ+i4LuXcYKoy5OZaYFDZQVtEWAu4PEzpVIVacnCcJKUZLemdn1Y1khjaG1lGtBJVafJ+8vus4oke3RWkKmGqxrU3aS3rhKPGL7ji9PKHdLZLtWIq7zDOulK558BpVYmEakPtZOPGMuKZk6eiHOPe0eTt6oxy7WbEdZi6lT3pWXgsl8ERgcfUp2nTnOnUhunCThPZ4q67/wBWZXWvV+pSltWyfE1pTcZd6dmv1R6uOdh5uSuS6HoXpRx1BKFVxxEedVN1F+ZNN+Zu2iOlLC1LKvTnRv8Abg+tprvaykvRnCqjyy3b14EYeu0zRm+rMDjqNeCqUakKsH9qElJeD5M9B8v4DStWk+xUqQfCUJyhNd6azR1fo418nXmsBjJKVV/wK7snV+5P73J8d2/eR0oEACQQABIAAEFFeqoQnUluhGUn4JXYGB1s1xwujlFVdqpVmm4UadtrZ96Tfsq/7yOR62dIOIxrcEnQo8KUJXb75StmzX9YdK1MXiKuJqNuVSTe++zHhFdyWRiZsKrq1r5liUyJMttgS2QQwQSLBFRRFiUQLhWX1b0r9GrRcn9XNpVFwXKfl+h2vR1ROCatmj58udM6N9OupTeFm+3SS2bvOVPh6bvQx5K/LWlvhntcsN1lB81mjjONTU5X5nddIw2oNc0ca1owjp1pcm3Y5457dckfxYynO6ty/QpTsy3F2dy9Vjua3M9Dzr0ZHswuIlCcKkW4yhJSjJb1JO6fqeCmy/T5AfUGrmlFi8JQxSt9ZTTkl9mospL+ZMyRzDoT0o5UsRg2/Yca0F3S7Mvio+p08IAAAAABidba6p6Pxk27f6asl+KUHGK9WjKmi9MGP6vA06KedfEQTXOEO2/ioAcKqvMsTZdnvLM2FUMokVNlIEJgFViAiopJQUZQ2VstsoXPZobSUsNXp4iN+xLtL3oP2l6fFI8NyGyT2R07/RxEatKNSLUozipJrc00c81+wnZc7ezJPyeXzL3RxpzsSwc37Hap3918PJns16j/AKeo+SX6o83qz0+6y5lY9FNXi1yzLCL9B5npeVVBF2mUqJMQOj9C0mtIVFzws/Tbg/kdtOHdDlRR0jn9vDVYL8SkpW9EzuJUAAAAAEHJem+u+twVPhGnVn5ynBf2nWzkHThB/SMHLg6FWPmpp/NAcsre0/FlmRer+0yxIKtsglkEBoQYKW7O4FYJAUZRIqIZRaZBXJFIHo0bjJUK0Kq+y8++PFG+63Y2NTR23Fp7c6SXrf5HOj2Sx0pYdYdvKNRSXgotfM4tXZiXdbZEw8qLkGW0Vo7ZvWiCKT3FTIrZdT8c8PiMNWvZLEwT743jf4N+p9HHy3g3/C/9r/tPqGjNSjGS3SimvBq5XKsAAAABBy/pxw94YGt7tStTf5lBr+hnUTR+mHDbejNu38HEUZt8k7w/WaA4JV3lmRfrrN+JYYVbZBLIIBTJFRDAmDyRJTB5IkokgkgCGihorZSwqACQJRUilFSCLtNl6R5keiLuiD3YWVurfKcn/SfR+qOIdTAYWb3ujFfy9n5HzYnaNP8AFU/SB9BdHGKU9H0ocaTcH4PtJ/8AL4FRtAAAAACDD644Lr9HYyit8sPUcVznFbUfjFGYInFNNPc00/BgfKGJ3v1POzIaXw/V1alL/bk4P8rt8jHsKtsglkASiiq8iot1AFJ5FwtU97LoAhkkMCGUlTZSAJKSQKiUUkpgVou02WUyuLA91f2KT+9U/sOrdD2lvrZYVvKrhlOP46c2recZf8TlNZpU6SfFzl4Lsq/qn6Gz9H2LdLSOCmnl1qpv8NROD/qA+hgAEAAAIBIHzp0k4HqdJ4qNspVOtj4VEp/rJryNRZ1bpxwGziMNiUsqlGVN/ipyvf0mvQ5VIKtMpZXIokwIKWGyLgRF5l2555MupgV3IIuRcCWUsNlLYEgpuSBUmVJlCKkBWiuG8tov4dZ57t78ALuNl2ox92nFet5f3GR1bxfVV4Vv9p9Z5xzXxsYec9qTlzdz04adlLvsviB9YU5qSUlmmk0+aZUa90f6Q+kaMwlRu8o0lSlz2qb2Lvx2U/M2EIAAAAANA6aMNGWj4VH7VPERt4SjJNfBehweYAVakWZMAClkAAUTK4sACbggBRspJARBIAEoqQAFcSuUrK3PeABES9TYAHdehKo3o+tFvKOLls916cG/idDACAAA/9k=',
  'https://lh3.googleusercontent.com/HuRtMx--VgAiIrBSneFR9SNUEuRT0dtDRjr699rkQY65zMWYM3NJ0zXzM2WuyAkqRieIAy7vp1vNnSZ_obJ1HYUuZLgIaFJgzG_YwTubJQ',
  'data:image/jpeg;base64,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',
]
export default Vue.extend({
  components: { GroupRiItem, GroupRiTab, MainViewer },
  created(){
    this.checkExpired()
  },
  data() : GroupViewType {
    return {
      isLoad : false,
      tab: 1,
      pagenationInfo:{
        send: {
          count: 10,
          page: 1,
          total: 20,
        },
        post: {
          count: 10,
          page: 1,
          total: 100,  
        }
      },
      tabs:[
        {
          label: '내가 요청한 목록',
          id: 1,
          value: 'send',
          total: 0,
        },
        {
          label:  '요청 받은 목록',
          id: 2,
          value: 'post',
          total: 0,
        }
      ],
      sendList: [],
      postList: [],
      self: this
    }
  },
  mounted(){
    this.tabs[0].total = this.pagenationInfo.send.total
    this.tabs[1].total = this.pagenationInfo.post.total

  },
  updated(){
    this.checkExpired()
  },
  methods:{
    changeTab(value : GroupTabsType){
      this.tab = value.id;
    },
    getPagingMethod(self : GroupViewType){
      return {
        isMore:{
          send(){ return self.pagenationInfo.send.total > self.sendList.length },
          post(){ return self.pagenationInfo.post.total > self.postList.length } 
        },
        incresePage:{
          send(){ return self.pagenationInfo.send.page++ },
          post(){ return self.pagenationInfo.post.page++ }   
        },
        /* TODO: 서버와 연결시 리펙토링 */          
        loadItem:{
          async send(){
            for(let i = 0; i < self.pagenationInfo.send.count ; i++){
              self.sendList.push({
                user: {
                  thurmbnailUrl : imgList[getRandomInt(0, 3)],
                  userNm: `요청 보낸 사람 ${self.sendList.length}`,
                  userSn: self.sendList.length
                },
                groupMsg: '나와 그룹가 되어주시겠어요?',
                groupStatus: getRandomInt(0,3)
              });
            }
          },
          async post(){
            for(let i = 0; i < self.pagenationInfo.post.count; i++){
              self.postList.push({
                user: {
                  thurmbnailUrl : imgList[getRandomInt(0, 3)],
                  userNm: `요청 받은 사람 ${self.postList.length}`,
                  userSn: self.postList.length
                },
                memberList: [],
                groupMsg: '나의 그룹가 되어주시겠어요?',
                groupStatus: getRandomInt(0,3)
              });
            }
          }
        }
      }
    },
    checkExpired(){
      if(!this.$cookies.get('accessToken')){
        alert('로그인 후 이용해주세요');
        this.$EventBus.$emit('clearUserInfo');
        this.$router.push('/signin');
      }
    },
    async loadMore(e) {
      const type = this.tabs[this.tab-1].value;
      const pagingMethod = this.getPagingMethod(this);
      await sleep(1000); /* 서버와 연결시 삭제 */
      if(await pagingMethod.isMore[type]()){
        await pagingMethod.loadItem[type]();
        e.loaded();
      }
      else{
        e.complete();
      }
    },
  }
});
</script>

<style lang="scss" scoped>



</style>