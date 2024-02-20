package com.supercoding.brandiStory.web.controller;

import com.supercoding.brandiStory.repository.entity.CartItemEntity;
import com.supercoding.brandiStory.service.CartService;
import com.supercoding.brandiStory.web.dto.CartItemBody;
import com.supercoding.brandiStory.web.dto.CartItemDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class CartController implements ApiController {
    private final CartService cartService;

   //이거 프로덕트 컨트롤러에 넣을지, 장바구니 컨트롤러에 넣을지 정하기
    //json형식으로 post보내면 되겠지. {productId랑 quantity, }
    @Operation(summary = "장바구니에 상품 추가")
    @PostMapping("/products/add-to-cart")
    public ResponseEntity<String> addToCart(@RequestBody CartItemBody cartItemBody){
        cartService.addToCart(cartItemBody);
        return ResponseEntity.ok("상품이 장바구니에 추가되었습니다.");
    }

    //로그인 후에는 그 유저의 장바구니만 조회되니까 유저별 장바구니 조회는 필요없겠지?
    //장바구니 id별로 불러오기 구현???? 장바구니에 한 아이템 말고 두가지 이상 담기도 추가?
    @Operation(summary = "장바구니 조회하기")
    @GetMapping("/carts")
    public List<CartItemDTO> getCartItems(){
        return cartService.getCartItems();
    }


    @PutMapping("/carts/{id}")
    public ResponseEntity<CartItemDTO> updateCartItem(@PathVariable String id, @RequestBody CartItemBody cartItemBody) {
        CartItemDTO updatedCartItemDTO = cartService.updateCartItemDTO(id, cartItemBody);
        return ResponseEntity.ok(updatedCartItemDTO);
    }

}




//    //요청문은 /carts/update/{productId}?newQunatity=2 이렇게 될 예정 productId는 집어넣어야한다.
//    @Operation(summary = "장바구니 수량 수정")
//    @PutMapping("/carts/update/{productId}")
//    public ResponseEntity<String> updateCart(@RequestParam String productId, @RequestParam Integer newQuantity) {
//        cartService.updateCart(productId, newQuantity);
//        return ResponseEntity.ok("장바구니 수량이 수정되었습니다.");
//    }

