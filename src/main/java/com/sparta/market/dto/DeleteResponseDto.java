package com.sparta.market.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteResponseDto {
    private String msg;
    private int statusCode;

    //이부분 블로그에서 메시지보내던부분하고 똑같음
    public DeleteResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
