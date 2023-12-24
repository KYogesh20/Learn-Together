package com.learntogether.learntogether.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class CreatePoolRequestDto {
    private String email;
    private String poolName;
    private String poolDescription;
}
