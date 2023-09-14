package com.sparta.market.controller;

import com.sparta.market.dto.DeleteResponseDto;
import com.sparta.market.dto.ItemRequestDto;
import com.sparta.market.dto.ItemResponseDto;
import com.sparta.market.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {
    private final ItemService itemService;

    // 판매 게시 글 작성
    @PostMapping("/post")
    public ItemResponseDto createItem(@RequestBody ItemRequestDto requestDto) { //, HttpServletRequest req 나중에 유저정보필요하면 이것!
        return itemService.createItem(requestDto); //, user.getUsername()
    }

    // 판매 게시글 전체 리스트 조회
    @GetMapping("/post")
    public List<ItemResponseDto> getItem() {
        return itemService.getItem();
    }

    // 판매 게시글 상세 조회
    @GetMapping("/post/{id}")
    public ResponseEntity<?> getOneItem(@PathVariable Long id) {
        try {
            ItemResponseDto responseDto = itemService.getOneItem(id);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            String jsonResponse = "{\"msg\": \"" + e.getMessage() + "\", \"statusCode\": 404}";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonResponse);
        }
    }

    // 판매 게시글 수정 (refactor : API response Type change)
    @PutMapping("/post/{id}")
    public ItemResponseDto updateItem(@PathVariable Long id, @RequestBody ItemRequestDto requestDto) {
        ItemResponseDto updatedItem = itemService.updateItem(id, requestDto);
        return new ItemResponseDto(updatedItem);
    }
        //return itemService.updateItem(id, requestDto); //ItremResponseDto를 반환하도록 수정 //, username

    // 선택 글 삭제 (refactor : API response Type change)
    @DeleteMapping("/post/{id}")
    //public ResponseEntity<String> deleteItem(@PathVariable Long id) { //, HttpServletRequest req
        //return itemService.deleteItem(id); //, username
    public ResponseEntity<DeleteResponseDto> deleteItem(@PathVariable Long id) {
        DeleteResponseDto responseDto = itemService.deleteItem(id);
        return ResponseEntity.ok(responseDto);
    }
}