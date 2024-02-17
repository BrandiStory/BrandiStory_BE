package com.supercoding.brandiStory.service;

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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
    private final ProductJpaRepository productJpaRepository;


    public List<ProductDTO> getAllProducts() {
        List<ProductEntity> productEntities = productJpaRepository.findAll();
        if (productEntities.isEmpty()) throw new NotFoundException("등록된 상품이 없습니다.");
// 전체 출력 :return productEntities.stream().map(ProductMapper.INSTANCE::productEntityToProductDTO).collect(Collectors.toList())
// Qunatity가 0인 상품은 제외하고 출력
        return productEntities.stream().filter(productEntity -> productEntity.getQuantity() > 0)
                .map(ProductMapper.INSTANCE::productEntityToProductDTO).collect(Collectors.toList());
    }


    public List<ProductDTO> getAllProductsWithImages() {
        List<ProductEntity> productEntitiesWithImages=productJpaRepository.findAllWithImages();
        if(productEntitiesWithImages.isEmpty()) throw new NotFoundException("등록된 이미지가 없습니다.");
        return productEntitiesWithImages.stream().filter(productEntity -> productEntity.getQuantity() > 0)
                .map(ProductMapper.INSTANCE::productEntityToProductDTO).collect(Collectors.toList());
    }

//    public Optional<ProductEntity> getProductWithImages(String id) {
//        Integer productIdInt = Integer.valueOf(id);
//        return productJpaRepository.findByIdWithImages(productIdInt);
//    }

//    public List<ProductDTO> getAllProducts() {
//        List<ProductEntity> productEntities = productJpaRepository.findAllWithImages();
//        if (productEntities.isEmpty()) throw new NotFoundException("등록된 상품이 없습니다.");
//
//        return productEntities.stream()
//                .filter(productEntity -> productEntity.getQuantity() > 0)
//                .map(productEntity -> {
//                    ProductDTO productDTO = ProductMapper.INSTANCE.productEntityToProductDTO(productEntity);
//                    // 이미지 파일 경로 추가
//                    productDTO.setImageDTOList(productEntity.getImageList());
//                    return productDTO;
//                })
//                .collect(Collectors.toList());
//    }



//이거 써서 코드 다시 만들어보기!    findByIdWithImages(@Param("productId") Long productId):

// Pagination 적용. http://localhost:8080/api/items-page?size=4&page=0 하고 send
//한페이지에 4개 들어갈지 8개 들어갈지 정하면 됨. 그리고 첫번째 페이지는0페이지?로 강의에서 배움

    public Page<ProductDTO> findAllWithPageable(Pageable pageable) {
//        Page<ProductEntity> productEntities = productJpaRepository.findAll(pageable);
//         return productEntities.map(ProductMapper.INSTANCE::productEntityToProductDTO);
//수량이 0인 제품은 제외하고 출력
        Page<ProductEntity> productEntities = productJpaRepository.findAll(pageable);
        List<ProductEntity> filteredEntities = productEntities.getContent()
                .stream()
                .filter(entity -> entity.getQuantity() > 0)
                .collect(Collectors.toList());
        Page<ProductDTO> filteredPage = new PageImpl<>(filteredEntities)
                .map(ProductMapper.INSTANCE::productEntityToProductDTO);
        return filteredPage;
    }
}

