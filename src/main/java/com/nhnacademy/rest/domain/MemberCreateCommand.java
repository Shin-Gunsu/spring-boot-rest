package com.nhnacademy.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class MemberCreateCommand {
    private String id;
    private String password;
    private String name;
    private Integer age;
    private Role role;
    @JsonProperty("class")
    private ClassType classType;
}