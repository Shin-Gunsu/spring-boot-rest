package com.nhnacademy.rest.service;

import com.nhnacademy.rest.domain.ChannelRequest;
import com.nhnacademy.rest.domain.DoorayMessengerRequest;
import com.nhnacademy.rest.domain.MessageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

public class MessengerSender {
    public void SendMessage(ChannelRequest channelRequest) {
        String uri = "https://nhnacademy.dooray.com/services/3204376758577275363/4045901689874472590/W0RgKMoPTUO3RejIIJVQcg";
        if(channelRequest.getChannel().equals("A")) {
            uri = "https://nhnacademy.dooray.com/services/3204376758577275363/4045905507212963259/WL5QAUhXSXC09zQjupasRA";
        }
        RestTemplate restTemplate = new RestTemplate();

//        String messageRequestJson = "{\n" +
//                "    \"botName\": \"봇이름\",\n" +
//                "    \"text\": \"기본메세지\",\n" +
//                "    \"attachments\": [\n" +
//                "        {\n" +
//                "            \"title\": \"메세지 타이틀\",\n" +
//                "            \"text\": \"안녕하세요..\",\n" +
//                "            \"titleLink\": \"http://naver.com\",\n" +
//                "            \"botIconImage\": \"https://static.d//nullableooray.com/static_images/dooray-bot.png\",\n" +
//                "            \"color\": \"red\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", Arrays.asList("application/json"));
        DoorayMessengerRequest doorayMessengerRequest =  new DoorayMessengerRequest("ㅋㅋㅋ",channelRequest.getText() );
        HttpEntity<DoorayMessengerRequest> entity = new HttpEntity<>(doorayMessengerRequest, headers);
        ResponseEntity<HashMap> exchange = restTemplate.exchange(uri, HttpMethod.POST, entity, HashMap.class);
        System.out.println(exchange.getStatusCode());
    }
}
