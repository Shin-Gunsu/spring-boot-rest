package com.nhnacademy.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    private String botName;
    private String text;
    private List<Attachment> attachments;
}