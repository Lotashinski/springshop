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

import com.github.lotashinski.api.dto.CategoryCollectionItemDto;
import com.github.lotashinski.api.dto.CategoryCriteriaDto;
import com.github.lotashinski.api.dto.CategoryDataDto;
import com.github.lotashinski.api.dto.CategoryDto;
import com.github.lotashinski.api.entity.Category;
import com.github.lotashinski.api.entity.Product;
import com.github.lotashinski.api.exceptions.ResourceNotFoundException;
import com.github.lotashinski.api.mapper.CategoryMapper;
import com.github.lotashinski.api.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

	@Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;
    
    @InjectMocks
    private CategoryServiceImpl categoryService;
	
    @SuppressWarnings("unchecked")
	@Test
    void findByCriteria_ShouldReturnListOfCategoryItems() {
    	CategoryCriteriaDto criteria = new CategoryCriteriaDto();
    	
        Category category1 = new Category();
        Category category2 = new Category();
        List<Category> categories = List.of(category1, category2);
        
        CategoryCollectionItemDto itemDto1 = new CategoryCollectionItemDto();
        CategoryCollectionItemDto itemDto2 = new CategoryCollectionItemDto();
        List<CategoryCollectionItemDto> expectedDtos = List.of(itemDto1, itemDto2);

        Mockito.when(categoryRepository.findAll(Mockito.any(Specification.class), Mockito.any(Sort.class)))
                .thenReturn(categories);
        Mockito.when(categoryMapper.toItemDtoList(categories))
                .thenReturn(expectedDtos);
        
        List<? extends CategoryCollectionItemDto> result = categoryService.findByCriteria(criteria);

        Assertions.assertEquals(expectedDtos, result);
        Mockito.verify(categoryRepository).findAll(Mockito.any(Specification.class), Mockito.eq(Sort.by("title")));
        Mockito.verify(categoryMapper).toItemDtoList(Mockito.anyList());
    }
    
    @Test
    void create() {
    	CategoryDataDto data = new CategoryDataDto();
    	Category category = new Category();
    	CategoryDto dto = new CategoryDto();
    	
    	Mockito.when(categoryMapper.toEntity(Mockito.any(CategoryDataDto.class), Mockito.any(Category.class)))
    		.thenReturn(category);
    	Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
    		.thenReturn(category);
    	Mockito.when(categoryMapper.toDto(Mockito.any(Category.class)))
    		.thenReturn(dto);
    	
    	CategoryDto result = categoryService.create(data);
    	
    	Assertions.assertEquals(result, dto);
    	Mockito.verify(categoryMapper).toEntity(data, category);
    	Mockito.verify(categoryRepository).save(category);
    	Mockito.verify(categoryMapper).toDto(category);
    }
    
    @Test
    void update_ifExists() {
    	CategoryDataDto data = new CategoryDataDto();
    	Category category = new Category();
    	CategoryDto dto = new CategoryDto();
    	Long identifier = 1L;
    	
    	Mockito.when(categoryRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.of(category));
    	Mockito.when(categoryMapper.toEntity(Mockito.any(CategoryDataDto.class), Mockito.any(Category.class)))
			.thenReturn(category);
    	Mockito.when(categoryMapper.toDto(Mockito.any(Category.class)))
    		.thenReturn(dto);
    	
    	CategoryDto result = categoryService.update(identifier, data);
    	
    	Assertions.assertEquals(result, dto);
    	Mockito.verify(categoryRepository).save(category);
    	Mockito.verify(categoryMapper).toDto(category);
    }
    
    @Test
    void update_ifNotExists() {
    	CategoryDataDto data = new CategoryDataDto();
    	Long identifier = 1L;
    	
    	Mockito.when(categoryRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.empty());
    	
    	Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryService.update(identifier, data));
    	Mockito.verify(categoryRepository).findById(identifier);
    }
    
    @Test
    void read_ifExists() {
    	Long identifier = 1L;
    	Category category = new Category();
    	CategoryDto dto = new CategoryDto();
    	
    	Mockito.when(categoryRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.of(category));
    	Mockito.when(categoryMapper.toDto(category))
    		.thenReturn(dto);
    	
    	CategoryDto result = categoryService.read(identifier);
    	
    	Assertions.assertEquals(result, dto);
    	Mockito.verify(categoryRepository).findById(identifier);
    	Mockito.verify(categoryMapper).toDto(category);
    }
    
    @Test
    void read_ifNotExists() {
    	Long identifier = 1L;
    	
    	Mockito.when(categoryRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.empty());
    	
    	Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryService.read(identifier));
    	
    	Mockito.verify(categoryRepository).findById(identifier);
    }
    
    @Test
    void delete_ifExists() {
    	Long identifier = 1L;
    	Category category = new Category();
    	
    	Mockito.when(categoryRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.of(category));
    	
    	categoryService.delete(identifier);
    	
    	Mockito.verify(categoryRepository).findById(identifier);
    	Mockito.verify(categoryRepository).delete(category);
    }
    
    @Test
    void delete_ifNotExists() {
    	Long identifier = 1L;
    	
    	Mockito.when(categoryRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.empty());
    	
    	Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryService.delete(identifier));
    	
    	Mockito.verify(categoryRepository).findById(identifier);
    }
    
    @Test
    void delete_WhenHasAnyProducts() {
    	Long identifier = 1L;
    	Category category = new Category();
    	Product product1 = new Product();
    	Product product2 = new Product();
    	category.addProduct(product1);
    	category.addProduct(product1);
    	
    	Mockito.when(categoryRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.of(category));
    	
    	categoryService.delete(identifier);
    	
    	Assertions.assertFalse(product1.getCategories().contains(category));
    	Assertions.assertFalse(product2.getCategories().contains(category));
    	Mockito.verify(categoryRepository).findById(identifier);
    	Mockito.verify(categoryRepository).delete(category);
    }
    
}
