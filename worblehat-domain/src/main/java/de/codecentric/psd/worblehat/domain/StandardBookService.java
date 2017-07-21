package de.codecentric.psd.worblehat.domain;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * The domain service class for book operations.
 */
@Service
@Transactional
public class StandardBookService implements BookService {
    public StandardBookService(){

    }

    @Autowired
    public StandardBookService(BorrowingRepository borrowingRepository, BookRepository bookRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
    }

    private BorrowingRepository borrowingRepository;

    private BookRepository bookRepository;

    @Override
    public void returnAllBooksByBorrower(String borrowerEmailAddress, String isbn, String title) {
        List<Borrowing> borrowingsByUser = borrowingRepository
                .findBorrowingsByBorrower(borrowerEmailAddress);
        for (Borrowing borrowing : borrowingsByUser) {
            String bookIsbn = borrowing.getBorrowerBook().getIsbn();
            String bookTitle = borrowing.getBorrowerBook().getTitle();

            if (bookIsbn == null && bookTitle == null) {
                borrowingRepository.delete(borrowing);
                continue;
            }

            if (bookIsbn != null && bookTitle != null) {
                if (bookIsbn.equals(isbn) && bookTitle.equals(title)){
                    borrowingRepository.delete(borrowing);
                    return;
                }
            }

            if (bookIsbn != null && bookIsbn.equals(isbn)) {
                borrowingRepository.delete(borrowing);
                return;
            }
            if (bookTitle != null && bookTitle.equals(title)) {
                borrowingRepository.delete(borrowing);
                return;
            }
        }
    }

    @Override
    public void borrowBook(Book book, String borrowerEmail) throws BookAlreadyBorrowedException {
        Borrowing borrowing = borrowingRepository.findBorrowingForBook(book);
        if (borrowing != null) {
            throw new BookAlreadyBorrowedException("Book is already borrowed");
        } else {
            book =findBookByIsbn(book.getIsbn());
            borrowing = new Borrowing(book, borrowerEmail, new DateTime());
            borrowingRepository.save(borrowing);
        }
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn); //null if not found
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAllBooks();
    }


    @Override
    public Book createBook(String title, String author, String edition, String isbn, int yearOfPublication, String description) {
        Book book = new Book(title, author, edition, isbn, yearOfPublication);
        book.setDescription(description);
        return bookRepository.save(book);
    }

    @Override
    public boolean bookExists(String isbn) {
        return findBookByIsbn(isbn) != null;
    }

    @Override
    public void deleteAllBooks() {
        borrowingRepository.deleteAll();
        bookRepository.deleteAll();
    }


}
