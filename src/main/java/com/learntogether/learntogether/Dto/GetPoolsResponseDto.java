package com.learntogether.learntogether.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class GetPoolsResponseDto {
    private String poolName;
    private String poolDescription;
    private String poolOwner;
}
