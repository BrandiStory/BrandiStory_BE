package com.supercoding.brandiStory.web.controller;

//import com.supercoding.brandiStory.repository.Entity.ImageEntity;
import com.supercoding.brandiStory.repository.entity.ProductEntity;
import com.supercoding.brandiStory.repository.products.ProductJpaRepository;
import com.supercoding.brandiStory.service.CartService;
import com.supercoding.brandiStory.service.ProductService;
import com.supercoding.brandiStory.service.exceptions.InvalidValueException;
import com.supercoding.brandiStory.web.dto.CartItemDTO;
import com.supercoding.brandiStory.web.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productDTO = productService.getAllProducts();
        return productDTO;
    }

    @Operation(summary = "pagination 지원하는 전체 상품 조회페이지")
    @GetMapping("/products-page")
    public Page<ProductDTO> findAllProductsPagination(Pageable pageable){
        return productService.findAllWithPageable(pageable);
    }


    @Operation(summary = "상품 상세 조회")
    @GetMapping("/products/{productId}")
    public ProductDTO getProduct(@PathVariable int productId) {
        return productService.getProduct(productId);
    }



}
