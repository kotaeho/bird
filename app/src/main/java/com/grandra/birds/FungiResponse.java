package com.grandra.birds;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(name = "response")
public class FungiResponse {
    @Element(name = "body")
    private FungiBody body;

    public void setBody(FungiBody body) {
        this.body = body;
    }

    public FungiBody getBody() {
        return body;
    }

    @Element(name = "header")
    private FungiHeader header;

    public void setHeader(FungiHeader header) {
        this.header = header;
    }

    public FungiHeader getHeader() {
        return header;
    }



}


