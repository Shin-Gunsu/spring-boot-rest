package com.nhnacademy.rest.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ClassType {
    A,B,C;

    @JsonCreator
    public static ClassType fromString(String value) {
        for(ClassType c : ClassType.values()) {
            if(c.name().equalsIgnoreCase(value)) {
                return c;
            }
        }
        return A;
    }
}
