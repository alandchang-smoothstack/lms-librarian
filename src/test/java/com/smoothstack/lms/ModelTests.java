package com.smoothstack.lms;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.smoothstack.lms.entity.Author;
import com.smoothstack.lms.entity.Book;
import com.smoothstack.lms.entity.BookCopy;
import com.smoothstack.lms.entity.BookCopyId;
import com.smoothstack.lms.entity.Borrower;
import com.smoothstack.lms.entity.Genre;
import com.smoothstack.lms.entity.LibraryBranch;
import com.smoothstack.lms.entity.Publisher;

public class ModelTests {

	@Test
	public void author() {
		Author author = new Author();
		author.setId(0L);
		author.setName("");
		author.setBooks(new ArrayList<>());
		author.getId();
		author.getName();
		author.getBooks();
	}

	@Test
	public void book() {
		Book book = new Book();
		book.setId(0L);
		book.setTitle("");
		book.setPublisher(new Publisher());
		book.setAuthors(new ArrayList<>());
		book.setGenres(new ArrayList<>());
		book.setLibraryBranches(new ArrayList<>());
		book.setBorrowers(new ArrayList<>());
		book.getId();
		book.getTitle();
		book.getPublisher();
		book.getAuthors();
		book.getGenres();
		book.getLibraryBranches();
		book.getBorrowers();
	}

	@Test
	public void bookCopyId() {
		BookCopyId bookCopyId = new BookCopyId();
		bookCopyId.setBookId(0L);
		bookCopyId.setLibraryBranchId(0L);
		bookCopyId.getBookId();
		bookCopyId.getLibraryBranchId();
	}

	@Test
	public void bookCopy() {
		BookCopy bookCopy = new BookCopy();
		bookCopy.setId(new BookCopyId(0L, 0L));
		bookCopy.setAmount(0);
		bookCopy.getAmount();
	}

	@Test
	public void borrower() {
		Borrower borrower = new Borrower();
		borrower.setCardNumber(0L);
		borrower.setName("");
		borrower.setAddress("");
		borrower.setPhone("");
		borrower.setLibraryBranches(new ArrayList<>());
		borrower.setBooks(new ArrayList<>());
		borrower.getCardNumber();
		borrower.getName();
		borrower.getAddress();
		borrower.getPhone();
		borrower.getLibraryBranches();
		borrower.getBooks();
	}

	@Test
	public void genre() {
		Genre genre = new Genre();
		genre.setId(0L);
		genre.setName("");
		genre.setBooks(new ArrayList<>());
		genre.getId();
		genre.getName();
		genre.getBooks();
	}

	@Test
	public void libraryBranch() {
		LibraryBranch libraryBranch = new LibraryBranch();
		libraryBranch.setId(0L);
		libraryBranch.setName("");
		libraryBranch.setAddress("");
		libraryBranch.setBooks(new ArrayList<>());
		libraryBranch.setBorrowers(new ArrayList<>());
		libraryBranch.getId();
		libraryBranch.getName();
		libraryBranch.getAddress();
		libraryBranch.getBooks();
		libraryBranch.getBorrowers();
	}

	@Test
	public void publisher() {
		Publisher publisher = new Publisher();
		publisher.setId(0L);
		publisher.setName("");
		publisher.setAddress("");
		publisher.setPhone("");
		publisher.setBooks(new ArrayList<>());
		publisher.getId();
		publisher.getName();
		publisher.getAddress();
		publisher.getPhone();
		publisher.getBooks();
	}

}
