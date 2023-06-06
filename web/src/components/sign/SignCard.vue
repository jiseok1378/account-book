<template>
    <sign-container>
        <sign-container-inner>
            <title-input class="card-inner-content" hide-details autofocus :title="'사용자 아이디'" placeholder="사용자 아이디" />
            <title-input class="card-inner-content" hide-details :title="'사용자 패스워드'" type="password" placeholder="사용자 패스워드" />
            <template v-if="type === 'signup'">
                <title-input class="card-inner-content" hide-details :title="'이름'" placeholder="이름" />
                <title-input class="card-inner-content" hide-details :title="'E-mail'" placeholder="E-mail" />
            </template>
            <cmmn-btn @click="(e)=> $emit('submit', e)" class="card-inner-content" width="100%" color="primary">{{submitTitle}}</cmmn-btn>
            <link-text v-if="type === 'signup'">
                <router-link to="/signin">아이디가 이미 있으신가요?</router-link>
            </link-text>
            <link-text v-else>
                <router-link to="/signup">아이디가 없으신가요?</router-link>
                <router-link to="/" class="card-link-text-right">ID/PW를 잊어버리셨나요?</router-link>
            </link-text>
            
        </sign-container-inner>
    </sign-container>
</template>

<script lang="ts">
import CmmnBtn from '@/components/global/button/CmmnBtn.vue';
import TitleInput from '@/components/global/input/TitleInput.vue';
import LinkText from '@/components/global/link/LinkText.vue';
import { SignContainer, SignContainerInner } from '@/styled-components/StyledComponents';
import Vue from 'vue';
export default Vue.extend({
    components: {
        SignContainer,
        SignContainerInner,
        TitleInput,
        CmmnBtn,
        LinkText
    },
    props:{
        submitTitle: {
            type : String,
        },
        type: {
            type: String,
            default: () => "signin"
        }
    }
})
</script>

<style lang="scss" scoped>
.card-inner-content{
    margin-bottom: 10px;
}
.card-link-text-right{
    margin-left: 10px;
}
</style>