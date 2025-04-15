package com.nhnacademy.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Locale;


@Getter
@AllArgsConstructor
public class Requester {
    private String ip;
    private Locale lang;

}
