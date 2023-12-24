package com.learntogether.learntogether.Dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class CreatePostRequestDto {
    String email;
    String content;
    String poolName;
}
