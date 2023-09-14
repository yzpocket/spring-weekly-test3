package com.sparta.market.repository;

import com.sparta.market.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    //List<Item> findAllByOrderByModifiedAtDesc(); //Audit으로 시간쓸때 이거쓰면 좋음
    //List<Item> findAllByIdContainsOrderByModifiedAtDesc(Long id);

}