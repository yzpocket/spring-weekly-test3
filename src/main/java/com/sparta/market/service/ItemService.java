package com.sparta.market.service;

import com.sparta.market.dto.ItemRequestDto;
import com.sparta.market.dto.ItemResponseDto;
import com.sparta.market.entity.Item;
import com.sparta.market.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository; // <- Item 타입으로 SimpleRepository 구현체 객체가 들어옴

    public ItemResponseDto createItem(ItemRequestDto requestDto) {
        // RequestDto -> Entity
        Item item = new Item(requestDto); //, username HttpSevReq res 안써서 아직은 필요없음

        // DB 저장
        Item saveItem = itemRepository.save(item);

        // Entity -> ResponseDto
        ItemResponseDto itemResponseDto = new ItemResponseDto(saveItem);

        return itemResponseDto;
    }

    // 게시글 조회
    public List<ItemResponseDto> getItem() {
        return itemRepository.findAll().stream().map(ItemResponseDto::new).toList(); //전체 목록 가져오는것
    }

    // 선택 게시글 조회 by 글번호
    public ItemResponseDto getOneItem(Long id) {
        Item item = findItem(id); // 글 존재 확인 검증 메소드
        return new ItemResponseDto(item);
    }

    // 선택 게시글 수정 by 글번호
    @Transactional
    public Item updateItem(Long id, ItemRequestDto requestDto) {
        Item item = findItem(id); // 글 존재 확인 검증 메소드
        item.update(requestDto); // 변경 감지가 적용됨
        return item; // 수정된 게시글 반환
    }

    // 선택 게시글 삭제 by 글번호
    @Transactional // 변경감지->삭제도 마찬가지 Transaction 환경필요
    public ResponseEntity<String> deleteItem(Long id) {
        Item item = findItem(id); // 글 존재 확인 검증 메소드
        itemRepository.delete(item); // 변경 감지가 적용됨
        return ResponseEntity.ok("{\"msg\": \"삭제 성공\", \"statusCode\": 200}");
    }

    // 글 존재 확인 메소드(중복제거용)
    private Item findItem(Long id){
        return itemRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 정보는 존재하지 않습니다.")
        );
    }


    //아래는 로그인 정보를 이용할때 쓰는거임

    //@Transactional
    //public Item updateItem(Long id, ItemRequestDto requestDto) {
    //    try {
    //        Item item = findItem(id); // 글 존재 확인 검증 메소드
    //        if (item.getUsername().equals(username)) {
    //            item.update(requestDto, username); // 변경 감지가 적용됨
    //            return item; // 수정된 게시글 반환
    //        } else {
    //            throw new IllegalAccessException("수정할 권한이 없습니다."); // 다른 사용자가 수정하지 못하게 방지
    //        }
    //    } catch (IllegalAccessException e) {
    //        // 수정 실패 시 예외 처리
    //        throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
    //    }
    //}

    //@Transactional //변경감지->삭제도 마찬가지 Transaction 환경필요
    //public ResponseEntity<String> deleteItem(Long id) {
    //    try {
    //        Item item = findItem(id); // 글 존재 확인 검증 메소드
    //        if (item.getUsername().equals(username)) {
    //            itemRepository.delete(item); // 변경 감지가 적용됨
    //            return ResponseEntity.ok("{\"msg\": \"삭제 성공\", \"statusCode\": 200}");
    //        } else {
    //            return ResponseEntity.ok("{\"msg\": \"삭제 실패\", \"statusCode\": 444}");
    //        }
    //    } catch (IllegalArgumentException e) {
    //        // 삭제 실패 시 예외 처리
    //        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    //    }
    //}
}