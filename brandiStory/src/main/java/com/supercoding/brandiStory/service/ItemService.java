package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.repository.items.ItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemJpaRepository itemJpaRepository;
}
