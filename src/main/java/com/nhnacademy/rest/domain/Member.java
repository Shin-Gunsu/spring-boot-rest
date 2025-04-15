package com.nhnacademy.rest.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member  {
    private String id;
    private String password;
    private String name;
    private Integer age;
    private Role role;
    @JsonProperty("class")
    @JsonSerialize(using = ToStringSerializer.class)
    private ClassType clazz;
}
