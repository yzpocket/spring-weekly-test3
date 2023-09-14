package com.sparta.market.dto;

import lombok.Getter;


@Getter
public class ItemRequestDto { // 정보주는 Dto
    private String title;
    private String username;
    private String content;
    private int price;
}
