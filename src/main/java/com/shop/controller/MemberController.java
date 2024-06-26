package com.shop.controller;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import com.shop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto",new MemberFormDto());
        return "members/memberForm";
    }
    @PostMapping("/new")
    public String memberForm(@Valid MemberFormDto memberFormDto,
                             BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "members/memberForm";
        }
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        memberService.saveMember(member);

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginMember(){
        return "/members/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요.");
        return "/members/memberLoginForm";

    }
}
