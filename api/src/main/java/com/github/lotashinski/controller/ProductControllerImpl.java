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

import com.github.lotashinski.dto.ProductCollectionItemDto;
import com.github.lotashinski.dto.ProductCriteriaDto;
import com.github.lotashinski.dto.ProductDataDto;
import com.github.lotashinski.dto.ProductDto;
import com.github.lotashinski.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductControllerImpl implements ProductController {

	private final ProductService service;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@GetMapping
	public ResponseEntity<List<? extends ProductCollectionItemDto>> findByCriteria(ProductCriteriaDto criteria) {
		return ResponseEntity.ok(service.findByCriteria(criteria));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PostMapping
	public ResponseEntity<ProductDto> create(@RequestBody ProductDataDto data) {
		return new ResponseEntity<>(service.create(data), HttpStatus.CREATED);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@GetMapping(path = "/{id:\\d+}")
	public ResponseEntity<ProductDto> read(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.read(id));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PutMapping(path = "/{id:\\d+}")
	public ResponseEntity<ProductDto> update(@PathVariable("id") Long id, @RequestBody ProductDataDto data) {
		return ResponseEntity.ok(service.update(id, data));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@DeleteMapping(path = "/{id:\\d+}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
}
