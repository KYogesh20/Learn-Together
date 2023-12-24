package com.learntogether.learntogether.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class BookmarkPostRequestDto {

    Long postId;
    String email;


}
