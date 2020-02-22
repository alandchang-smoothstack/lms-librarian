package com.smoothstack.lms;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.smoothstack.lms.controller.LibrarianController;
import com.smoothstack.lms.entity.Book;
import com.smoothstack.lms.entity.BookCopy;
import com.smoothstack.lms.entity.BookCopyId;
import com.smoothstack.lms.entity.LibraryBranch;
import com.smoothstack.lms.service.LibrarianService;

@SpringBootTest
public class LibrarianServiceTests {

	@InjectMocks
	LibrarianController librarianController;

	@Mock
	LibrarianService librarianService;

	@Test
	public void getLibraryBranches() throws SQLException {
		when(librarianService.getLibraryBranches()).thenThrow(SQLException.class);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
				librarianController.getLibraryBranches().getStatusCode());
	}

	@Test
	public void getLibraryBranchesEmpty() throws SQLException {
		when(librarianService.getLibraryBranches()).thenReturn(new ArrayList<LibraryBranch>());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, librarianController.getLibraryBranches().getStatusCode());
	}

	@Test
	public void getLibraryBranchById() throws SQLException {
		when(librarianService.getLibraryBranchById(anyLong())).thenThrow(SQLException.class);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
				librarianController.getLibraryBranchById(1L).getStatusCode());
	}

	@Test
	public void updateLibraryBranch() throws SQLException {
		LibraryBranch libraryBranch = new LibraryBranch();
		libraryBranch.setId(1L);
		libraryBranch.setName("TestName");
		libraryBranch.setAddress("TestAddress");
		doThrow(SQLException.class).when(librarianService).updateLibraryBranch(any());
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
				librarianController.updateLibraryBranch(libraryBranch).getStatusCode());
	}

	@Test
	public void getBooks() throws SQLException {
		when(librarianService.getBooks()).thenThrow(SQLException.class);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, librarianController.getBooks().getStatusCode());
	}

	@Test
	public void getBooksEmpty() throws SQLException {
		when(librarianService.getBooks()).thenReturn(new ArrayList<Book>());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, librarianController.getBooks().getStatusCode());
	}

	@Test
	public void addBookCopy() throws SQLException {
		BookCopy bookCopy = new BookCopy();
		bookCopy.setId(new BookCopyId(1L, 1L));
		bookCopy.setAmount(1);
		doThrow(SQLException.class).when(librarianService).addBookCopy(any());
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
				librarianController.addBookCopy(bookCopy).getStatusCode());
	}

	@Test
	public void getBookCopyById() throws SQLException {
		when(librarianService.getBookCopyById(anyLong(), anyLong())).thenThrow(SQLException.class);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
				librarianController.getBookCopy(1, 1).getStatusCode());
	}

	@Test
	public void updateBookCopy() throws SQLException {
		BookCopy bookCopy = new BookCopy();
		bookCopy.setId(new BookCopyId(1L, 1L));
		bookCopy.setAmount(1);
		doThrow(SQLException.class).when(librarianService).updateBookCopy(any());
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
				librarianController.updateBookCopy(bookCopy).getStatusCode());
	}
}
