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
    private final ItemRepository itemRepository; // <- Item 타입으로 SimpleRepository 구현체 객체가 들어옴

    // 게시글 작성 (refactor : API response Type change)
    public ItemResponseDto createItem(ItemRequestDto requestDto) {
        // RequestDto -> Entity
        Item item = new Item(requestDto); //, username HttpSevReq res 안써서 아직은 필요없음

        // DB 저장
        Item saveItem = itemRepository.save(item);

        // Entity -> ResponseDto
        //ItemResponseDto itemResponseDto = new ItemResponseDto(saveItem);
        //return itemResponseDto;
        // 이부분으로 해결된줄알았지만 -> 객체를 생성하여 데이터를 이동시키는 부분은 맞지만,
        // 해당 객체를 변수에 할당하고 그 변수를 반환하고 있기 때문에 엔티티와 DTO 사이의 변환이라고 보기 어렵다.
        //따라서 앞으론 위 표현보다는 아래 표현으로 변경하자.

        // Entity -> ResponseDto를 바로 반환
        return new ItemResponseDto(saveItem);
    }



    // 게시글 조회 (refactor : API response Type change)
    public List<ItemResponseDto> getItem() {
        //return itemRepository.findAll().stream().map(ItemResponseDto::new).toList(); //전체 목록 가져오는것
        //이것 또한 위 표현은 객체를 변수에 할당하고 그 변수를 반환하고 있기 때문에 엔티티와 DTO 사이의 변환이라고 보기 어렵다.
        //따라서 앞으론 위 표현보다는 아래 표현으로 변경하자.
        List<Item> itemList = itemRepository.findAll(); // 엔티티 목록을 가져옴
        return itemList.stream()
                .map(ItemResponseDto::new) // 엔티티를 DTO로 변환 // 이부분이 Dto객체하나씩 만드는데 = Row 하나 라는 부분인듯
                .collect(Collectors.toList()); // DTO 목록을 반환
    }

    // 선택 게시글 조회 by 글번호 (refactor : API response Type change)
    public ItemResponseDto getOneItem(Long id) {
        Item item = findItem(id); // 글 존재 확인 검증 메소드
        return new ItemResponseDto(item); // 이부분은 이미 Entity->ResponseDto 객체로 변환 및 반환하고있다.
    }

    // 선택 게시글 수정 by 글번호 (refactor : API response Type change)
    @Transactional
    public ItemResponseDto updateItem(Long id, ItemRequestDto requestDto) {
        Item item = findItem(id); // 글 존재 확인 검증 메소드
        item.update(requestDto); // 변경 감지가 적용됨
        return new ItemResponseDto(item); // 수정된 게시글을 DTO로 변환하여 반환
    }

    // 선택 게시글 삭제 by 글번호 (refactor : API response Type change)
    @Transactional // 변경감지->삭제도 마찬가지 Transaction 환경필요
    //public ResponseEntity<String> deleteItem(Long id) {
    //    Item item = findItem(id); // 글 존재 확인 검증 메소드
    //    itemRepository.delete(item); // 변경 감지가 적용됨
    //    return ResponseEntity.ok("{\"msg\": \"삭제 성공\", \"statusCode\": 200}");
    //}
    //예외처리하던 HttpStatus 보내던것처럼 반환할 DeleteResponseDto를 추가해서 새로해야겠음
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