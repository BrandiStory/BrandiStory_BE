package com.supercoding.brandiStory.web.controller;

import com.supercoding.brandiStory.repository.Entity.ProductEntity;
import com.supercoding.brandiStory.service.ProductService;
import com.supercoding.brandiStory.web.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/") //ApiController상속해줘서 생략
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ApiController {

    private final ProductService productService;

    @Operation(summary = "상품 전체 조회 *품절제외")
    @GetMapping("/products")
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

}
