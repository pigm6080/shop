package com.shop.service;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest { //테스트에서는 생성자 주입이 안된다.

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberFormDto memberFormDto = MemberFormDto.builder()
                .email("test@email.com")
                .name("홍길동")
                .address("기흥구")
                .password("1234")
                .build();
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        return member;
    }

    @Test
    @DisplayName("회원가입 테스트")
    @Commit
    public void saveMemberTest(){
        Member member = createMember();
        System.out.println(member);

        Member member1 =memberService.saveMember(member);
        System.out.println(member1);

    }

    @Test
    @DisplayName("중복 회원가입테스트")
    void saveDuplicateMemberTest() {
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class, () ->{
            memberService.saveMember(member2);});

        assertEquals("이미 가입된 회원입니다.", e.getMessage());
    }



}