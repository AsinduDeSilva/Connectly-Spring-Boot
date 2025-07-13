package com.groupc.connectly.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private long postId;

    @NotNull
    private String title;

    @NotNull
    private String content;

//    @NotNull
//    private LocalDateTime createdAt;

//    private User author;
}
