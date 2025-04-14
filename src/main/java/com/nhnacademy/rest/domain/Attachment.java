package com.nhnacademy.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
    private String title;
    private String text;
    private String titleLink;
    private String botIconImage;
    private String color;

}