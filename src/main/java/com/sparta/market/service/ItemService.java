package com.sparta.market.service;

import com.sparta.market.dto.DeleteResponseDto;
import com.sparta.market.dto.ItemRequestDto;
import com.sparta.market.dto.ItemResponseDto;
import com.sparta.market.entity.Item;
import com.sparta.market.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    // 게시글 작성 (refactor : API response Type change)
    public ItemResponseDto createItem(ItemRequestDto requestDto) {
        // RequestDto -> Entity
        Item item = new Item(requestDto);

        // DB 저장
        Item saveItem = itemRepository.save(item);

        // Entity -> ResponseDto를 바로 반환
        return new ItemResponseDto(saveItem);
    }

    // 게시글 조회 (refactor : API response Type change)
    public List<ItemResponseDto> getItem() {
        List<Item> itemList = itemRepository.findAll(); // 엔티티 목록을 가져옴
        return itemList.stream()
                .map(ItemResponseDto::new) // 이부분이 Dto객체하나씩 만드는데 = Row 하나 라는 부분인듯
                .collect(Collectors.toList()); // DTO 목록을 반환
    }

    // 선택 게시글 조회 by 글번호 (refactor : API response Type change)
    public ItemResponseDto getOneItem(Long id) {
        Item item = findItem(id); // 글 존재 확인 검증 메소드
        return new ItemResponseDto(item);
    }

    // 선택 게시글 수정 by 글번호 (refactor : API response Type change)
    @Transactional
    public ItemResponseDto updateItem(Long id, ItemRequestDto requestDto) {
        Item item = findItem(id); // 글 존재 확인 검증 메소드
        item.update(requestDto); // 변경 감지가 적용됨
        return new ItemResponseDto(item);
    }

    // 선택 게시글 삭제 by 글번호 (refactor : API response Type change)
    @Transactional
    public DeleteResponseDto deleteItem(Long id) {
        Item item = findItem(id); // 글 존재 확인 검증 메소드
        itemRepository.delete(item); // 변경 감지가 적용됨
        return new DeleteResponseDto("삭제 성공", 200);
    }

    // 글 존재 확인 메소드(중복제거용)
    private Item findItem(Long id){
        return itemRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 정보는 존재하지 않습니다.")
        );
    }
}