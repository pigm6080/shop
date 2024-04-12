package com.shop.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberFormDto { //가입화면으로 부터 넘어오는 값을 감을 dto생성.

    private String name;

    private String email;

    private String password;

    private String address;

}
