package com.nhnacademy.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class DoorayMessengerRequest {
    private String botName;
    private String text;
}
