package de.codecentric.psd.worblehat.web.formdata;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * This class represent the form data of the return book form.
 */
public class ReturnAllBooksFormData {

	@NotEmpty(message = "{empty.returnAllBookFormData.emailAddress}") 
	@Email(message = "{notvalid.returnAllBookFormData.emailAddress}")
	private String emailAddress;
	private String isbn;
	private String title;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
