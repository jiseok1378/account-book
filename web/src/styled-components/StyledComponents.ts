import styled from 'vue-styled-components';

const topTextProps = {
    fontSize : Number
}

const FlexContainerProps = {
    virtical : Boolean
}

export const TopText =  styled('span', topTextProps)`
        font-weight: bold;
        font-size : ${props => props.fontSize}px;
    ` 

export const FlexContainer = styled('div', FlexContainerProps)`
    display : flex;
    flex-direction : ${props => props.virtical ? 'row' : 'column'};
    width: 100%;
`

export const SignContainer = styled('div')`
    display: flex;
    flex-direction: column;
    padding: 30px 0px;
    margin-left: auto;
    margin-right: auto;
`

export const SignContainerInner = styled('div')`
    width: 350px;
    min-height: 300px;
    padding: 10px 20px;
`
export const LinkTextContainer = styled('div')`
    text-align: center;
    width: 100%;
`

export const CircleLogo = styled('span')`
    background: var(--v-primary-lighten3);
    width: 50px;
    height: 50px;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 100%;
`
export const LogoTitle = styled('h4')`
    margin-top: 10px;
`

export const Logo = styled('div')`
    width: 100%;
    heigt: 80px
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-top: 70px;
    align-items: center;
`