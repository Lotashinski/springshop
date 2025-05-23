package com.github.lotashinski.service.impl;

import com.github.lotashinski.exceptions.ResourceNotFoundException;

class ExceptionUtills {

	static <T, I> ResourceNotFoundException createNotFounException(Class<T> clazz, I identifier) {
		String message = String.format("Resource \"%s\" with identifier \"%s\" not found", clazz.getSimpleName(),
				identifier.toString());

		return new ResourceNotFoundException(message);
	}

}
