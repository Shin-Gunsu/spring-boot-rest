package com.nhnacademy.rest.controller;


import com.nhnacademy.rest.domain.*;

import com.nhnacademy.rest.service.MemberService;
import com.nhnacademy.rest.service.MessengerSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/me")
    public Member me() {
        return new Member("id","password","name", 1, Role.MEMBER, ClassType.A);
    }

    @PostMapping("/me")
    public Member post(@RequestBody MemberCreateCommand command) {
        return new Member(command.getId(),command.getPassword(), command.getName(), command.getAge(), command.getRole(), command.getClassType());
    }

    // 멤버 등록
    @PostMapping("/members")
    public ResponseEntity addMember(@RequestBody MemberCreateCommand memberCreateCommand) {
        Member member = memberService.saveMember(memberCreateCommand);
        return ResponseEntity.ok(member);
    }

    //멤버 조회
    @GetMapping("/members/{memberId}")
    public Member getMember(@PathVariable String memberId) {
        return memberService.getMember(memberId);
    }

    //멤버 전체 조회
    @GetMapping("/members")
    public List<Member> getMembers(Pageable pageable) {
        return memberService.getMembers();
    }

    //멤버 삭제
    @DeleteMapping("/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable String memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }

//    //멤버 수정
//    @PutMapping("/members/{memberId}")
//    public Member updateMember(@PathVariable String memberId, @RequestBody MemberCreateCommand memberCreateCommand) {
//
//    }

@GetMapping("/redis-test")
public ResponseEntity<?> testRedis() {
    Map<String,Object> map = memberService.test();
    return ResponseEntity.ok(map);
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
