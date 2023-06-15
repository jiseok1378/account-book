
<template>
    <div class="d-flex">
        <v-row class="group-item d-flex justify-center">
            <v-col 
                class="d-flex justify-start align-center" 
                :cols="cols" 
                md="2" >
                <thumbnail-container :src="item.user.thurmbnailUrl"/>
                <label class="user-name"><b>{{item.user.userNm}}</b></label>
            </v-col>
            <v-col class="d-flex justify-start align-center" :cols="cols" md="7">{{ item.groupMsg }}</v-col>
            <v-col class="d-flex justify-start align-center" :cols="cols" md="2">
                 <router-link to="/" v-if="Object.keys(item).includes('memberList')">
                    멤버 수: {{ item.memberList.length }}
                </router-link>
            </v-col> 
            <v-col :class="`d-flex justify-center align-center`"  :cols="cols" md="1">
                <v-btn color="primary" class="detail-link" @click="goGroupDetail">
                    <label class="txt" v-html="statusCode[item.groupStatus]"></label>
                </v-btn> 
                <!--  -->
            </v-col>
        </v-row>
    </div>
</template>

<script lang="ts">
import ThumbnailContainer from '@/components/global/img/ThumbnailContainer.vue';
import Vue from 'vue';
export default Vue.extend({
  components: { ThumbnailContainer },
    data(){
        return {
            cols: 12,
            statusCode:{
                0: '수락<br />대기중',
                1: '수락',
                2: '거절',
                3: '그룹 끊김'
            }
        }
    },
    
    props:{
        item:{
            default: Object
        },
    },
    methods:{
        goGroupDetail(){
            this.$router.push("/")
        }
    }
})
</script>

<style lang="scss" scoped>
.group-item{
    margin: 10px 0px;
    border: solid 1px;
    border-color: var(--v-primary-darken2);
    border-radius: 10px;
    min-height: 30px;
    overflow: hidden;
    .user-name{
        margin-left: 5px;
    }
    &:hover{
        background: var(--v-primary-lighten3);
    }
    .detail-link{
        font-weight: bold;
        width: 100%;
        background: var(--v-primary-base);
        text-align: center;
        .txt{
            cursor: inherit;
        }
        &.disabled{
        pointer-events: none;
        cursor: not-allowed;
        background: var(--v-secondary-lighten4) !important;
        }
    }
}

</style>