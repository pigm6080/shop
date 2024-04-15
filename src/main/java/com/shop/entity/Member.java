package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Entity //엔티티
@Setter @Getter @ToString
@NoArgsConstructor //기본생성자 생성
@AllArgsConstructor //모든 필드 포한하는 생성자 생선
@Builder
public class Member extends BaseEntity{

    @Id //기본키
    @Column(name = "member_id") //컬럼 이름
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 생성되는 기본키 갑설정
    private Long id;

    private String name;

    @Column(unique = true) // 유일한 값이여야한다.
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING) //열거형 상수를 문자열로 지정한다.
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){

        Member member = Member.builder()
                .role(Role.USER)
                .email(memberFormDto.getEmail())
                .address(memberFormDto.getAddress())
                .name(memberFormDto.getName())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .build();
        String password = memberFormDto.getPassword();;

        return member;
    }

}
