package com.learntogether.learntogether.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class RegisterResponseDto {
    private String firstname;
    private String lastname;
    private String username;
    private String email;

}
