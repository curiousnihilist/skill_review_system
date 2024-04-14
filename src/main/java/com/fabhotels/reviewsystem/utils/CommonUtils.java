package com.fabhotels.reviewsystem.utils;

import com.fabhotels.reviewsystem.dto.ResponseObj;

public class CommonUtils {

    private CommonUtils(){
    }

    public static ResponseObj getResponseObjForFailure(String message) {
        ResponseObj responseObj = new ResponseObj();
        responseObj.setResult(false);
        responseObj.setErrorMessage(message);
        return responseObj;
    }

    public static ResponseObj getResponseObjForSuccess(Object data) {
        ResponseObj responseObj = new ResponseObj();
        responseObj.setResult(true);
        responseObj.setData(data);
        return responseObj;
    }
}
