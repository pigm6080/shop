package com.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberFormDto { //가입화면으로 부터 넘어오는 값을 감을 dto생성.

    @NotBlank( message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty( message = "이멜일은 필수 입력 값입니다.")
    @Email( message = "이메일 형식으로 입력해 주세요.")
    private String email;

    @NotEmpty( message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8,max=16,message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotEmpty( message = "주소는 필수 입력 값입니다. ")
    private String address;

}
