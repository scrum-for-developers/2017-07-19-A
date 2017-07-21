package de.codecentric.psd.worblehat.web.formdata;

import de.codecentric.psd.worblehat.web.validation.ISBN;
import de.codecentric.psd.worblehat.web.validation.ISBNUniqueValidator;
import de.codecentric.psd.worblehat.web.validation.Unique;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Form data object from the borrow view.
 */
public class BookBorrowFormData {

	@NotEmpty(message = "{empty.borrowCmd.isbn}") 
	@ISBN(message = "{notvalid.borrowCmd.isbn}")
	@Unique(service = ISBNUniqueValidator.class, message = "{isbn.unique.violation}")
	private String isbn;

	@NotEmpty(message = "{empty.borrowCmd.email}")
	@Email(message = "{notvalid.borrowCmd.email}")
	private String email;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
