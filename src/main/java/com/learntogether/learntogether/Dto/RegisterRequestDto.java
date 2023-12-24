package com.learntogether.learntogether.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class RegisterRequestDto {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;

}
