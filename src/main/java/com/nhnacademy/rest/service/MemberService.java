package com.nhnacademy.rest.service;

import com.nhnacademy.rest.domain.*;
import com.nhnacademy.rest.exception.MemberAlreadyExistException;
import com.nhnacademy.rest.exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private String HASH_NAME = "Member:";

    public Member saveMember(MemberCreateCommand memberCreateCommand) {

//        TODO
//         중복체크 Error
        boolean exists = redisTemplate.opsForHash().hasKey(HASH_NAME, memberCreateCommand.getId());
        if (exists) {
            throw new MemberAlreadyExistException(memberCreateCommand.getId());
        }

        Member member = new Member(memberCreateCommand.getId(), memberCreateCommand.getPassword(), memberCreateCommand.getName(), memberCreateCommand.getAge(), memberCreateCommand.getRole(), memberCreateCommand.getClassType());
        redisTemplate.opsForHash().put(HASH_NAME, member.getId(), member);
        return member;
    }

    public Member getMember(String id) {
        Object o = redisTemplate.opsForHash().get(HASH_NAME, id);
        if (o == null) {
            throw new MemberNotFoundException(id);
        }
        return (Member) o;
    }

    public List<Member> getMembers() {
        return redisTemplate.opsForHash().values(HASH_NAME).stream().map(o -> (Member) o).toList();
    }

    public void deleteMember(String id) {
        redisTemplate.opsForHash().delete(HASH_NAME, id);
    }

    public Map<String, Object> test() {
        try {
            String testKey = "test:key";
            String testValue = "연결 테스트 " + System.currentTimeMillis();

            // 값 저장
            redisTemplate.opsForValue().set(testKey, testValue);

            // 값 조회
            String retrievedValue = (String) redisTemplate.opsForValue().get(testKey);
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("savedValue", testValue);
            result.put("retrievedValue", retrievedValue);
            return result;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public Member login(MemberLoginRequest loginRequest) {
        return new Member("gunsu","gunnsu","gunsu",100, Role.MEMBER, ClassType.A);
    }
}