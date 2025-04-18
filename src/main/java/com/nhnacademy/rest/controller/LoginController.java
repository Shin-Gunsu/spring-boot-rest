//package com.nhnacademy.rest.controller;
//
//
//import com.nhnacademy.rest.domain.Member;
//import com.nhnacademy.rest.domain.MemberLoginRequest;
//import com.nhnacademy.rest.service.MemberService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//    private final MemberService memberService;
//
//    public LoginController(MemberService memberService) {
//        this.memberService = memberService;
//    }
//
//    @GetMapping
//    public String loginPage() {
//        return "login";
//    }
//    @PostMapping
//    public ModelAndView processLogin(MemberLoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response)  {
//        Member memberResponse = memberService.login(loginRequest);
//        ModelAndView mav = new ModelAndView("home");
//        mav.addObject("loginName", memberResponse.getName());
//        return mav;
//    }
//}
