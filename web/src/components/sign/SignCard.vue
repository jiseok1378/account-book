<template>
    <sign-container>
        <sign-container-inner>
            <title-input class="card-inner-content" hide-details :title="'User ID'" placeholder="사용자 ID" />
            <title-input class="card-inner-content" hide-details :title="'User Password'" placeholder="사용자 패스워드" />
            <cmmn-btn class="card-inner-content" width="100%" color="primary">로그인</cmmn-btn>
            <link-text>
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