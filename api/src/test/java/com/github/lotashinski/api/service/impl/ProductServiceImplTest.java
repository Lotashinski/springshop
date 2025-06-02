package com.github.lotashinski.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;
import com.github.lotashinski.api.dto.ProductCriteriaDto;
import com.github.lotashinski.api.dto.ProductDataDto;
import com.github.lotashinski.api.dto.ProductDto;
import com.github.lotashinski.api.entity.Product;
import com.github.lotashinski.api.exceptions.ResourceNotFoundException;
import com.github.lotashinski.api.mapper.ProductMapper;
import com.github.lotashinski.api.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	@Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;
    
    @InjectMocks
    private ProductServiceImpl productService;
	
    @SuppressWarnings("unchecked")
	@Test
    void findByCriteria_ShouldReturnListOfProductItems() {
    	ProductCriteriaDto criteria = new ProductCriteriaDto();
    	
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = List.of(product1, product2);
        
        ProductCollectionItemDto itemDto1 = new ProductCollectionItemDto();
        ProductCollectionItemDto itemDto2 = new ProductCollectionItemDto();
        List<ProductCollectionItemDto> expectedDtos = List.of(itemDto1, itemDto2);

        Mockito.when(productRepository.findAll(Mockito.any(Specification.class), Mockito.any(Sort.class)))
                .thenReturn(products);
        Mockito.when(productMapper.toItemDtoList(products))
                .thenReturn(expectedDtos);
        
        List<? extends ProductCollectionItemDto> result = productService.findByCriteria(criteria);

        Assertions.assertEquals(expectedDtos, result);
        Mockito.verify(productRepository).findAll(Mockito.any(Specification.class), Mockito.eq(Sort.by("title")));
        Mockito.verify(productMapper).toItemDtoList(Mockito.anyList());
    }
    
    @Test
    void create() {
    	ProductDataDto data = new ProductDataDto();
    	Product product = new Product();
    	ProductDto dto = new ProductDto();
    	
    	Mockito.when(productMapper.toEntity(Mockito.any(ProductDataDto.class), Mockito.any(Product.class)))
    		.thenReturn(product);
    	Mockito.when(productRepository.save(Mockito.any(Product.class)))
    		.thenReturn(product);
    	Mockito.when(productMapper.toDto(Mockito.any(Product.class)))
    		.thenReturn(dto);
    	
    	ProductDto result = productService.create(data);
    	
    	Assertions.assertEquals(result, dto);
    	Mockito.verify(productMapper).toEntity(data, product);
    	Mockito.verify(productRepository).save(product);
    	Mockito.verify(productMapper).toDto(product);
    }
    
    @Test
    void update_ifExists() {
    	ProductDataDto data = new ProductDataDto();
    	Product product = new Product();
    	ProductDto dto = new ProductDto();
    	Long identifier = 1L;
    	
    	Mockito.when(productRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.of(product));
    	Mockito.when(productMapper.toEntity(Mockito.any(ProductDataDto.class), Mockito.any(Product.class)))
			.thenReturn(product);
    	Mockito.when(productMapper.toDto(Mockito.any(Product.class)))
    		.thenReturn(dto);
    	
    	ProductDto result = productService.update(identifier, data);
    	
    	Assertions.assertEquals(result, dto);
    	Mockito.verify(productRepository).save(product);
    	Mockito.verify(productMapper).toDto(product);
    }
    
    @Test
    void update_ifNotExists() {
    	ProductDataDto data = new ProductDataDto();
    	Long identifier = 1L;
    	
    	Mockito.when(productRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.empty());
    	
    	Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.update(identifier, data));
    	Mockito.verify(productRepository).findById(identifier);
    }
    
    @Test
    void read_ifExists() {
    	Long identifier = 1L;
    	Product product = new Product();
    	ProductDto dto = new ProductDto();
    	
    	Mockito.when(productRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.of(product));
    	Mockito.when(productMapper.toDto(Mockito.any(Product.class)))
    		.thenReturn(dto);
    	
    	ProductDto result = productService.read(identifier);
    	
    	Assertions.assertEquals(result, dto);
    	Mockito.verify(productRepository).findById(identifier);
    	Mockito.verify(productMapper).toDto(product);
    }
    
    @Test
    void read_ifNotExists() {
    	Long identifier = 1L;
    	
    	Mockito.when(productRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.empty());
    	
    	Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.read(identifier));
    	
    	Mockito.verify(productRepository).findById(identifier);
    }
    
    @Test
    void delete_ifExists() {
    	Long identifier = 1L;
    	Product product = new Product();
    	
    	Mockito.when(productRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.of(product));
    	
    	productService.delete(identifier);
    	
    	Assertions.assertTrue(product.getIsDeleted());
    	Mockito.verify(productRepository).findById(identifier);
    	Mockito.verify(productRepository, Mockito.never()).delete(product);
    }
    
    @Test
    void delete_ifNotExists() {
    	Long identifier = 1L;
    	
    	Mockito.when(productRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.empty());
    	
    	Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.delete(identifier));
    	
    	Mockito.verify(productRepository).findById(identifier);
    }

}
