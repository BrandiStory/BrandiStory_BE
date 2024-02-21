package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.repository.entity.ImageEntity;
import com.supercoding.brandiStory.repository.entity.ProductEntity;
import com.supercoding.brandiStory.repository.products.ProductJpaRepository;
import com.supercoding.brandiStory.service.exceptions.NotFoundException;
import com.supercoding.brandiStory.service.mapper.ProductMapper;
import com.supercoding.brandiStory.web.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class ProductService {
    private final ProductJpaRepository productJpaRepository;


//    public List<ProductDTO> getAllProducts() {
//        List<ProductEntity> productEntities = productJpaRepository.findAll();
//        if (productEntities.isEmpty()) throw new NotFoundException("등록된 상품이 없습니다.");
//// 전체 출력 :return productEntities.stream().map(ProductMapper.INSTANCE::productEntityToProductDTO).collect(Collectors.toList())
//// Qunatity가 0인 상품은 제외하고 출력
//        return productEntities.stream().filter(productEntity -> productEntity.getQuantity() > 0)
//                .map(ProductMapper.INSTANCE::productEntityToProductDTO).collect(Collectors.toList());
//    }
//
//    public List<ProductDTO> getAllProductsWithImages() {
//        List<ProductEntity> productEntitiesWithImages=productJpaRepository.findAllWithImages();
//        if(productEntitiesWithImages.isEmpty()) throw new NotFoundException("등록된 이미지가 없습니다.");
//        return productEntitiesWithImages.stream().filter(productEntity -> productEntity.getQuantity() > 0)
//                .map(ProductMapper.INSTANCE::productEntityToProductDTO).collect(Collectors.toList());
//    }


// Pagination 적용. http://localhost:8080/api/items-page?size=4&page=0 하고 send
//한페이지에 4개 들어갈지 8개 들어갈지 정하면 됨. 그리고 첫번째 페이지는0페이지?로 강의에서 배움
// 전체 제품 조회 - 페이지네이션 적용, 수량0 제외, 이미지 불포함
//    public Page<ProductDTO> findAllWithPageable(Pageable pageable) {
////        Page<ProductEntity> productEntities = productJpaRepository.findAll(pageable);
////         return productEntities.map(ProductMapper.INSTANCE::productEntityToProductDTO);
////수량이 0인 제품은 제외하고 출력
//        Page<ProductEntity> productEntities = productJpaRepository.findAll(pageable);
//        List<ProductEntity> filteredEntities = productEntities.getContent()
//                .stream()
//                .filter(entity -> entity.getQuantity() > 0)
//                .collect(Collectors.toList());
//        Page<ProductDTO> filteredPage = new PageImpl<>(filteredEntities)
//                .map(ProductMapper.INSTANCE::productEntityToProductDTO);
//        return filteredPage;
//    }

    //전체제품 조회 - 페이지네이션 적용, 수량 0 제외, 이미지 포함, 완성본
        public Page<ProductDTO> findAllWithPageableWithImages(Pageable pageable) {
            Page<ProductEntity> productEntities = productJpaRepository.findAll(pageable);
            List<ProductEntity> filteredEntities = productEntities.getContent()
                    .stream()
                    .filter(entity -> entity.getQuantity() > 0)
                    .collect(Collectors.toList());
            Page<ProductDTO> filteredPage = new PageImpl<>(filteredEntities)
                    .map(ProductMapper.INSTANCE::productEntityToProductDTO);
            return filteredPage;
        }

    public ProductDTO getProductDetail(int productId) {
        // image랑 join해서 가져오는 코드
        Optional<ProductEntity> productEntity = productJpaRepository.findByIdWithImages(productId);
        if (productEntity.isEmpty()) throw new NotFoundException("상품을 찾을 수 없습니다.");
        System.out.println("productEntity.get() = " + productEntity.get().getImageList());
        return ProductMapper.INSTANCE.productEntityToProductDTO(productEntity.get());
        //상세 정보 들고 오기
//        ProductEntity productEntity2 = productJpaRepository.findById(productId)
//                .orElseThrow(() -> new NotFoundException("상품을 찾을 수 없습니다."));
//        return ProductMapper.INSTANCE.productEntityToProductDTO(productEntity2);
    }
}

