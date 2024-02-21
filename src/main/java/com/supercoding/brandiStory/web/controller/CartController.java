package com.supercoding.brandiStory.web.controller;
import com.supercoding.brandiStory.service.CartService;
import com.supercoding.brandiStory.web.dto.CartItemBody;
import com.supercoding.brandiStory.service.exceptions.NotAcceptException;
import com.supercoding.brandiStory.service.exceptions.NotFoundException;
import com.supercoding.brandiStory.web.dto.CartItemDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@Slf4j
public class CartController implements ApiController {
    private final CartService cartService;

    @Operation(summary = "장바구니에 상품 추가")
    @PostMapping("/products/add-to-cart")
    public ResponseEntity<String> addToCart(@RequestBody CartItemBody cartItemBody){
        cartService.addToCart(cartItemBody);
        return ResponseEntity.ok("상품이 장바구니에 추가되었습니다.");
    }

    @Operation(summary = "장바구니 조회하기")
    @GetMapping("/carts")
    public List<CartItemDTO> getCartItems(){
        return cartService.getCartItems();
    }

    @Operation(summary = "유저별 장바구니 조회하기 | json요청문:{usersId:7}")
    @PostMapping("/carts")
    public List<CartItemDTO> getCartItemsByUsersId(@RequestBody Map<String, Integer> requestBody){
        Integer usersId = requestBody.get("usersId");
        return cartService.getCartItemsByUsersId(usersId);
    }

    //userId로 조회한 값으로 나온 장바구니 리스트들을 BODY에 JSON값으로 넣어주면 총가격 출력됨.
    @Operation(summary="장바구니에 담긴 상품 가격 총 합계")
    @PostMapping("/carts/total-price")
    public ResponseEntity<String> calculateTotalPrice(@RequestBody List<CartItemDTO> cartItems) {
        Integer sumTotalPrice = cartService.calculateTotalPrice(cartItems);
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedPrice = formatter.format(sumTotalPrice);
        String finalPrice = "장바구니 총합계 = " + formattedPrice+ "원";
        return ResponseEntity.ok(finalPrice);
    }

    @Operation(summary = "장바구니 수정하기")
    @PutMapping("/carts/{cartId}")
    public ResponseEntity<CartItemDTO> updateCartItem(@PathVariable String cartId, @RequestBody CartItemBody cartItemBody) {
        CartItemDTO updatedCartItemDTO = cartService.updateCartItemDTO(cartId, cartItemBody);
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

