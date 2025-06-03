package com.github.lotashinski.api.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;
import com.github.lotashinski.api.dto.ProductCriteriaDto;
import com.github.lotashinski.api.dto.ProductDataDto;
import com.github.lotashinski.api.dto.ProductDto;
import com.github.lotashinski.api.entity.Product;
import com.github.lotashinski.api.mapper.ProductMapper;
import com.github.lotashinski.api.repository.ProductRepository;
import com.github.lotashinski.api.repository.specification.ProductSpecification;
import com.github.lotashinski.api.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
	private final ProductMapper productMapper;

	@Transactional(readOnly = true)
	@Override
	public List<? extends ProductCollectionItemDto> findByCriteria(ProductCriteriaDto criteria) {
		log.info("Load products by criteria [criteria: {}]", criteria);
		
		Specification<Product> spec = ProductSpecification
				.isNotDeleted()
				.and(ProductSpecification.hasIdentifiersIn(criteria.getIds()))
				.and(ProductSpecification.hasExistsInCategory(criteria.getCategory()))
				.and(ProductSpecification.hasExistsInCategories(criteria.getCategories()));
		
		List<Product> entities = productRepository.findAll(spec, Sort.by("title"));
		
		return productMapper.toItemDtoList(entities);
	}

	@Transactional
	@Override
	public ProductDto create(ProductDataDto dto) {
		log.info("Create new product [data: {}]", dto);
		
		Product entity = productMapper.toEntity(dto, new Product());
		productRepository.save(entity);
		
		return productMapper.toDto(entity);
	}

	@Transactional(readOnly = true)
	@Override
	public ProductDto read(Long id) {
		log.info("Read product by id [id: {}]", id);
		
		Product entity = getEntityOrThrow(id);
		
		return productMapper.toDto(entity);
	}

	@Transactional
	@Override
	public ProductDto update(Long id, ProductDataDto data) {
		log.info("Update product by id [id: {}, data: {}]", id, data);
		
		Product entity = getEntityOrThrow(id);
		productMapper.toEntity(data, entity);
		productRepository.save(entity);
		
		return productMapper.toDto(entity);
	}
	 
	@Transactional
	@Override
	public void delete(Long id) {
		log.info("Delete product by id [id: {}]", id);
		
		Product entity = getEntityOrThrow(id);
		entity.setIsDeleted(true);
	}
	
	private Product getEntityOrThrow(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> ExceptionUtills.createNotFounException(Product.class, id));
	}
	
}
