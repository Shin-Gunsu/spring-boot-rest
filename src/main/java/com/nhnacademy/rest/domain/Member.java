package com.nhnacademy.rest.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {
    private String name;
    private Integer age;
    @JsonSerialize(using = ToStringSerializer.class)
    private Role role;
    @JsonProperty("class")
    @JsonSerialize(using = ToStringSerializer.class)
    private ClassType clazz;
}
