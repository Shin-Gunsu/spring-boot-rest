package com.nhnacademy.rest.controller;


import com.nhnacademy.rest.domain.*;

import com.nhnacademy.rest.service.MessengerSender;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
public class MemberController {
    @GetMapping("/me")
    public Member me() {
        return new Member("name", 1, Role.admin, ClassType.A);
    }

    @PostMapping("/me")
    public Member post(@RequestBody MemberCreateCommand command) {
        return new Member(command.getName(), command.getAge(), command.getRole(), command.getClassType());
    }

    @PostMapping("/members")
    public ResponseEntity addMember(@RequestBody MemberCreateCommand memberCreateCommand,
                                    Requester requester) {
        System.out.println(requester);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/members")
    public List<Member> getMembers(Pageable pageable) {
        System.out.println("page" + pageable.getPageNumber());
        System.out.println("size" + pageable.getPageSize());
        return Arrays.asList(new Member("신건영", 20, Role.admin, ClassType.A));
    }

    @PostMapping("/send-message")
    public void  sendMessage(@RequestBody ChannelRequest channelRequest) {

//        String uri = "https://nhnacademy.dooray.com/services/3204376758577275363/4045901689874472590/W0RgKMoPTUO3RejIIJVQcg";
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<MessageRequest> requestEntity = new HttpEntity<>(messageRequest, headers);
//        ResponseEntity<MessageRequest> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, MessageRequest.class);
//        MessageRequest body = response.getBody();

        MessengerSender sender = new MessengerSender();
        sender.SendMessage(channelRequest);
    }
}
