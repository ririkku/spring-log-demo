package com.example.log.logdemo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OtherAppResponse {

    String value;

    public OtherAppResponse(@JsonProperty("key") String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
