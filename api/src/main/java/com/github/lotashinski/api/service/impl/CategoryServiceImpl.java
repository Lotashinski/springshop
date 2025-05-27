package com.github.lotashinski.api.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.lotashinski.api.entity.Category;
import com.github.lotashinski.api.mapper.CategoryMapper;
import com.github.lotashinski.api.repository.CategoryRepository;
import com.github.lotashinski.api.service.CategoryService;
import com.github.lotashinski.api.dto.CategoryCollectionItemDto;
import com.github.lotashinski.api.dto.CategoryCriteriaDto;
import com.github.lotashinski.api.dto.CategoryDataDto;
import com.github.lotashinski.api.dto.CategoryDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	private final CategoryMapper categoryMapper;

	@Transactional(readOnly = true)
	@Override
	public List<? extends CategoryCollectionItemDto> findByCriteria(CategoryCriteriaDto criteria) {
		log.info("Load categories by criteria [criteria: {}]", criteria);
		
		// TODO change to query
		List<Category> entities = categoryRepository.findAll(Sort.by("title"));

		return categoryMapper.toItemDtoList(entities);
	}

	@Transactional
	@Override
	public CategoryDto create(CategoryDataDto dto) {
		log.info("Create new category [data: {}]", dto);
		
		Category category = categoryMapper.toEntity(dto, new Category());
		categoryRepository.save(category);

		return categoryMapper.toDto(category);
	}

	@Transactional(readOnly = true)
	@Override
	public CategoryDto read(Long id) {
		log.info("Read category by id [id: {}]", id);
		
		Category entity = getEntityOrThrow(id);
		
		return categoryMapper.toDto(entity);
	}
	
	@Transactional
	@Override
	public CategoryDto update(Long id, CategoryDataDto data) {
		log.info("Update category by id [id: {}, data: {}]", id, data);
		
		Category entity = getEntityOrThrow(id);
		categoryMapper.toEntity(data, entity);
		categoryRepository.save(entity);
		
		return categoryMapper.toDto(entity);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		log.info("Delete category by id [id: {}]", id);
		
		Category entity = getEntityOrThrow(id);
		categoryRepository.delete(entity);
	}

	private Category getEntityOrThrow(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> ExceptionUtills.createNotFounException(Category.class, id));
	}

}
