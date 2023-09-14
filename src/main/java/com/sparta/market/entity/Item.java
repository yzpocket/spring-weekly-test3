package com.sparta.market.entity;

import com.sparta.market.dto.ItemRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "item") // 맵핑된 DB 테이블 : item
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment(MySQL)
    private Long id; // 게시글 번호 (DB 인덱스)

    @Column(name = "title", nullable = false)
    private String title; //게시글 제목

    @Column(name = "content", nullable = false, columnDefinition = "TEXT") //<- text로!
    private String content; //게시글 내용

    @Column(name = "price, nullable = false")
    private int price; //가격

    @Column(name = "username", nullable = false)
    private String username; //작성자

    public Item(ItemRequestDto requestDto) { //, String tokenUsername
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = requestDto.getUsername();
        this.price = requestDto.getPrice();
        //this.username = tokenUsername; // 사용자 이름 설정 아직은 로그인 유저정보 필요없어요
    }

    public void update(ItemRequestDto requestDto){ //, String username
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.price = requestDto.getPrice();
    }

    //public String getPassword(BlogRequestDto requestDto){
    //    this.password = requestDto.getPassword();
    //    return password;
    //}
}
