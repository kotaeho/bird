package com.grandra.birds;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(name = "header")
public class FungiHeader {
    @PropertyElement  (name = "resultCode")
    private String resultCode;
    @PropertyElement  (name = "resultMsg")
    private String resultMsg;



    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    // Add getters for resultCode and resultMsg
    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }
}
