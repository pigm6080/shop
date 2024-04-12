package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional // 트랜잭셔널. 커밋 안됨.
@RequiredArgsConstructor //생성자 의존성 주입이다.
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    /*
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());

        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    */

    private void validateDuplicateMember(Member member) { // 회원의 이메일이 등록되어있는지 검증하는 메서드
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        //Optional 값이 null 인지 아닌지 확인하고 처리하는 객체 타임?
        if(findMember.isPresent()){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }


}
