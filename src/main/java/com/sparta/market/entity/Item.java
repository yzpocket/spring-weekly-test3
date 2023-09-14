package com.sparta.market.entity;

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


}
