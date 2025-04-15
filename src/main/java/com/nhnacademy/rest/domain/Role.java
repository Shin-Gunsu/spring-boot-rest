package com.nhnacademy.rest.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ADMIN,
    MEMBER;

    @JsonCreator
    public static Role fromString(String value) {
        for(Role r : Role.values()) {
            if(r.toString().equals(value)) {
                return r;
            }
        }
        return MEMBER;
    }
    @JsonValue
    public String toJson(){
        return this.name().toLowerCase();
    }
}
