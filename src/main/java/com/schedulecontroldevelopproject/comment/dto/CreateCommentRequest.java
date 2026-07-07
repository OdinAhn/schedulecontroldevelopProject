package com.schedulecontroldevelopproject.comment.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

    private Long scheduleId;
    private Long userId;

    @NotEmpty(message = "댓글 내용을 적으셔야 합니다.")
    private String content;

}
