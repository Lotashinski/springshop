package com.github.lotashinski.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.lotashinski.dto.ProductCollectionItemDto;
import com.github.lotashinski.dto.ProductCriteriaDto;
import com.github.lotashinski.dto.ProductDataDto;
import com.github.lotashinski.dto.ProductDto;
import com.github.lotashinski.entity.Product;
import com.github.lotashinski.mapper.ProductMapper;
import com.github.lotashinski.repository.ProductRepository;
import com.github.lotashinski.service.ProductService;

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
		
		// TODO change to query
		List<Product> entities = productRepository.findAll(Sort.by("title"));
		
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
		productRepository.delete(entity);
	}
	
	private Product getEntityOrThrow(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> ExceptionUtills.createNotFounException(Product.class, id));
	}
	
}
