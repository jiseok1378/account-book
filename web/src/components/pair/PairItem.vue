
<template>
    <div class="d-flex">
        <v-row class="pair-item d-flex justify-center">
            <v-col class="d-flex justify-start align-center" :cols="cols" sm="2" >
                <thurmbnail-wrapper>
                    <user-thurmbnail :src="item.thurmbnailUrl"/>
                </thurmbnail-wrapper>
                <label class="user-name"><b>{{item.userNm}}</b></label>
            </v-col>
            <v-col class="d-flex justify-start align-center" :cols="cols" sm="7">{{ item.pairMsg }}</v-col>
            <v-col class="d-flex justify-end align-center " :cols="cols" sm="3">
                <v-btn class="btn" color="primary" :disabled="item.pairStatus != 1">
                   {{ statusCode[item.pairStatus] }}
                </v-btn>
            </v-col>
        </v-row>
    </div>
</template>

<script lang="ts">
import { PairItemType } from '@/@types/global-types';
import { ThurmbnailWrapper, UserThurmbnail } from '@/styled-components/StyledComponents';
import Vue from 'vue';
export default Vue.extend({
    components : {UserThurmbnail, ThurmbnailWrapper},
    data(){
        return {
            cols: 12,
            statusCode:{
                0: '수락 대기중',
                1: '수락',
                2: '거절',
                3: '페어 끊김'
            }
        }
    },
    props:{
        item:{
            default: Object as () => PairItemType
        },
    },
    mounted(){
        console.log(this.item)
    },
    methods:{

    }
})
</script>

<style lang="scss" scoped>
.pair-item{
    margin: 10px 0px;
    border: solid 1px;
    border-color: var(--v-primary-base);
    border-radius: 10px;
    .user-name{
        margin-left: 5px;
    }
    &:hover{
        background: var(--v-primary-lighten3);
    }
    .btn{
        width: 80px;
    }
    @media (max-width: 780px) {
        .btn{
            width: 100%;
        }
    }
}

</style>