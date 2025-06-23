package com.github.lotashinski.api.service.impl;

import com.github.lotashinski.api.exceptions.ResourceNotFoundException;

class ExceptionUtills {

	static <T, I> ResourceNotFoundException createNotFounException(Class<T> clazz, I identifier) {
		String message = String.format("Resource \"%s\" with identifier \"%s\" not found", clazz.getSimpleName(),
				identifier.toString());

		return new ResourceNotFoundException(message);
	}

}
