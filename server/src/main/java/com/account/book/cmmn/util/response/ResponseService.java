package com.account.book.cmmn.util.response;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    public <T> SingleResult<T> getSingleResult(T data){
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }
    public <T> ListResult<T> getListResult(List<T> data){
        ListResult<T> result = new ListResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    public CommonResult getSuccessResult(){
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }
    public CommonResult getFailResult(){
        CommonResult result = new CommonResult();
        setFailResult(result);
        return result;
    }

    private void setSuccessResult(CommonResult result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
    private void setFailResult(CommonResult result){
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}
