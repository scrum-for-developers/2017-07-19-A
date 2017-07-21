package de.codecentric.psd.worblehat.web.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.codecentric.psd.worblehat.domain.BookService;

@Service
public class ISBNUniqueValidator implements FieldValueExists {

	private final BookService bookService;

	@Autowired
	public ISBNUniqueValidator(final BookService bookService) {
		this.bookService = bookService;
	}
	
	@Override
	public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
		if (value == null) {
			return false;
		}
		return this.bookService.bookExists(value.toString());
	}

}
