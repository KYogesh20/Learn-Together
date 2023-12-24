package com.learntogether.learntogether.Dto;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder

public class UpvotePostRequestDto {

    private Long postId;
    private String email;

}
