package com.learntogether.learntogether.Dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class GetPostsResponseDto {
    private String poolName;
    private String content;
    private Date date;

}
