import { apiParam } from "./api-type"
import { isEmpty, isNonEmpty }  from "@/util/safeType/safe-type"
import axios, { AxiosRequestConfig, AxiosResponse } from "axios"

export default {
    get : async (url : string, param? : apiParam[] | undefined , config? : AxiosRequestConfig | undefined ) : Promise<AxiosResponse<any, any>>=> {
        let newUrl : string = url;
        if( isNonEmpty(param) ){
           newUrl = `${url}?${(param as apiParam[]).map(p => `${p.key}=${p.value}`).join("&")}`         
        }
        try{
            return axios.get( newUrl, config );
        }catch(e){
            return e as Promise<AxiosResponse<any, any>>;
        }
        
    } 

}