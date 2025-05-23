package com.github.lotashinski.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.lotashinski.dto.CategoryCriteriaDto;
import com.github.lotashinski.dto.CategoryDataDto;
import com.github.lotashinski.dto.CategoryDto;
import com.github.lotashinski.exceptions.ResourceNotFoundException;
import com.github.lotashinski.dto.CategoryCollectionItemDto;
import com.github.lotashinski.service.CategoryService; 

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

	private final CategoryService service;
	
	/**
	 * Load by criteria ({@link CategoryCriteriaDto}) categories and sort them by {@code title} 
	 * 
	 * @param criteria use as a condition. {@code null} fields do not limit the result
	 * @return sorted collection of categories ({@link CategoryCollectionItemDto})
	 */
	@GetMapping
	public ResponseEntity<List<? extends CategoryCollectionItemDto>> findByCriteria(CategoryCriteriaDto criteria) {
		return ResponseEntity.ok(service.findByCriteria(criteria));
	}
	
	/**
	 * Create new category
	 * 
	 * @param data data for {@link Category}
	 * @return created entity as {@link CategoryDto} 
	 */
	@PostMapping
	public ResponseEntity<CategoryDto> create(@RequestBody CategoryDataDto data) {
		return new ResponseEntity<>(service.create(data), HttpStatus.CREATED);
	}
	
	/**
	 * Get category via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Category} not found
	 * @param id identifier of {@link Category}
	 * @return category as {@link CategoryDto}
	 */
	@GetMapping(path = "/{id:\\d+}")
	public ResponseEntity<CategoryDto> read(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.read(id));
	}
	
	/**
	 * Update category via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Category} not found
	 * @param id identifier of {@link Category}
	 * @param data data for {@link Category}
	 * @return category as {@link CategoryDto}
	 */
	@PutMapping(path = "/{id:\\d+}")
	public ResponseEntity<CategoryDto> update(@PathVariable("id") Long id, @RequestBody CategoryDataDto data) {
		return ResponseEntity.ok(service.update(id, data));
	}
	
	/**
	 * Delete category via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Category} not found
	 * @param id identifier of {@link Category}
	 */
	@DeleteMapping(path = "/{id:\\d+}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
}
