import { getParam } from "./api-type"
import { isEmpty, isNonEmpty }  from "@/util/safeType/safe-type"
import axios, { AxiosRequestConfig, AxiosResponse } from "axios"

const defaultConfig : AxiosRequestConfig | undefined = {
    validateStatus : (status) => {
        return true
    } 
}

export default {
    get : async (url : string, param? : getParam[] | undefined , config? : AxiosRequestConfig | undefined ) : Promise<AxiosResponse<any, any>>=> {
        let newUrl : string = url;
        if( isNonEmpty(param) ){
           newUrl = `${url}?${(param as getParam[]).map(p => `${p.key}=${p.value}`).join("&")}`         
        }
        return axios.get( newUrl, config != undefined ? Object.assign(config,defaultConfig) : defaultConfig  );

    },
    post : async (url : string, param : any, config? : AxiosRequestConfig | undefined) : Promise<AxiosResponse<any,any>> => {

        return axios.post(url, param, config != undefined ? Object.assign(config,defaultConfig) : defaultConfig)

    }
}