package com.sparta.market.dto;

import com.sparta.market.entity.Item;
import lombok.Getter;

@Getter
public class ItemResponseDto { // response Dto
    private Long id;
    private String username;
    private String title;
    private String content;
    private int price;
    //private LocalDateTime createdAt; 나중에 시간필요하면 이것!
    //private LocalDateTime modifiedAt;

    public ItemResponseDto(Item item) {
        this.id = item.getId();
        this.content = item.getContent();
        this.title = item.getTitle();
        this.price = item.getPrice();
        this.username = item.getUsername();
        //this.createdAt = blog.getCreatedAt();
        //this.modifiedAt = blog.getModifiedAt();
    }
    public ItemResponseDto(String username, String title, String contents, int price) { //, LocalDateTime createdAt
        this.title = title;
        this.content = contents;
        this.price = price;
        this.username = username;
        //this.createdAt = createdAt;
    }

    public ItemResponseDto(ItemResponseDto updatedItem) {
        this.id = updatedItem.getId();
        this.title = updatedItem.getTitle();
        this.content = updatedItem.getContent();
        this.price = updatedItem.getPrice();
        this.username = updatedItem.getUsername();
    }

    public static ItemResponseDto fromItem(Item item) { // 필요한 정보만 내보내기
        return new ItemResponseDto(item.getUsername(), item.getTitle(), item.getContent(), item.getPrice()); //, item.getCreatedAt()
    }
}