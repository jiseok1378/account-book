import styled from 'vue-styled-components';

const topTextProps = {
    fontSize : Number
}

export const TopText =  styled('span', topTextProps)`
        font-size : ${props => props.fontSize}px;
    ` 