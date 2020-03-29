package com.smoothstack.lms;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.smoothstack.lms.controller.LibrarianController;
import com.smoothstack.lms.model.Book;
import com.smoothstack.lms.model.Branch;
import com.smoothstack.lms.model.Copies;
import com.smoothstack.lms.service.LibrarianService;

@SpringBootTest
class LibrarianControllerTests {

	@InjectMocks
	private LibrarianController librarianController;

	@Mock
	private LibrarianService librarianService;

	@Mock
	private Validator validator;

	@Test
	void getBranches() {
		List<Branch> mockList = Mockito.mock(List.class);
		Mockito.when(mockList.isEmpty()).thenReturn(false);
		Mockito.when(librarianService.getBranches()).thenReturn(mockList);
		Assertions.assertEquals(HttpStatus.OK, librarianController.getBranches().getStatusCode());
	}

	@Test
	public void getBranchesEmptyResult() {
		List<Branch> mockList = Mockito.mock(List.class);
		Mockito.when(mockList.isEmpty()).thenReturn(true);
		Mockito.when(librarianService.getBranches()).thenReturn(mockList);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, librarianController.getBranches().getStatusCode());
	}

	@Test
	void getBranchById() {
		Optional<Branch> branch = Optional.of(Mockito.mock(Branch.class));
		Mockito.when(librarianService.getBranchById(Mockito.anyLong())).thenReturn(branch);
		Assertions.assertEquals(HttpStatus.OK, librarianController.getBranchById(Mockito.anyLong()).getStatusCode());
	}

	@Test
	void getBranchByIdEmptyResult() {
		Mockito.when(librarianService.getBranchById(Mockito.anyLong())).thenReturn(Optional.empty());
		Assertions.assertEquals(HttpStatus.NOT_FOUND,
				librarianController.getBranchById(Mockito.anyLong()).getStatusCode());
	}

	@Test
	void updateBranch() {
		Branch branch = Mockito.mock(Branch.class);
		Mockito.when(librarianService.updateBranch(branch)).thenReturn(true);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, librarianController.updateBranch(branch).getStatusCode());
	}

	@Test
	void updateBranchFailedValidation() {
		Branch branch = Mockito.mock(Branch.class);
		Set violations = Mockito.mock(Set.class);
		Mockito.when(validator.validate(branch)).thenReturn(violations);
		Mockito.when(violations.isEmpty()).thenReturn(false);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, librarianController.updateBranch(branch).getStatusCode());
	}

	@Test
	void updateBranchFailure() {
		Branch branch = Mockito.mock(Branch.class);
		Mockito.when(librarianService.updateBranch(branch)).thenReturn(false);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, librarianController.updateBranch(branch).getStatusCode());
	}

	@Test
	void getBooks() {
		List<Book> mockList = Mockito.mock(List.class);
		Mockito.when(mockList.isEmpty()).thenReturn(false);
		Mockito.when(librarianService.getBooks()).thenReturn(mockList);
		Assertions.assertEquals(HttpStatus.OK, librarianController.getBooks().getStatusCode());
	}

	@Test
	public void getBooksEmptyResult() throws Exception {
		List<Book> mockList = Mockito.mock(List.class);
		Mockito.when(mockList.isEmpty()).thenReturn(true);
		Mockito.when(librarianService.getBooks()).thenReturn(mockList);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, librarianController.getBooks().getStatusCode());
	}

	@Test
	void getCopiesById() {
		Optional<Copies> copies = Optional.of(Mockito.mock(Copies.class));
		Mockito.when(librarianService.getCopiesById(Mockito.anyLong(), Mockito.anyLong())).thenReturn(copies);
		Assertions.assertEquals(HttpStatus.OK,
				librarianController.getCopiesById(Mockito.anyLong(), Mockito.anyLong()).getStatusCode());
	}

	@Test
	void getCopiesByIdEmpty() {
		Mockito.when(librarianService.getCopiesById(Mockito.anyLong(), Mockito.anyLong())).thenReturn(Optional.empty());
		Assertions.assertEquals(HttpStatus.NOT_FOUND,
				librarianController.getCopiesById(Mockito.anyLong(), Mockito.anyLong()).getStatusCode());
	}

	@Test
	void addCopies() {
		Copies copies = Mockito.mock(Copies.class);
		Book book = Mockito.mock(Book.class);
		Branch branch = Mockito.mock(Branch.class);
		Mockito.when(copies.getBook()).thenReturn(book);
		Mockito.when(copies.getBranch()).thenReturn(branch);
		Mockito.when(copies.getBook().getBookId()).thenReturn(1L);
		Mockito.when(copies.getBranch().getBranchId()).thenReturn(1L);
		Mockito.when(copies.getCopiesAmount()).thenReturn(1);
		Mockito.doNothing().when(librarianService).addCopies(copies);
		Assertions.assertEquals(HttpStatus.CREATED, librarianController.addCopies(copies).getStatusCode());
	}

	@Test
	void addCopiesFailedValidation() {
		Copies copies = Mockito.mock(Copies.class);
		Set violations = Mockito.mock(Set.class);
		Mockito.when(validator.validate(copies)).thenReturn(violations);
		Mockito.when(violations.isEmpty()).thenReturn(false);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, librarianController.addCopies(copies).getStatusCode());
	}

	@Test
	void updateCopies() {
		Copies copies = Mockito.mock(Copies.class);
		Mockito.when(librarianService.updateCopies(copies)).thenReturn(true);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, librarianController.updateCopies(copies).getStatusCode());
	}

	@Test
	void updateCopiesFailedValidation() {
		Copies copies = Mockito.mock(Copies.class);
		Set violations = Mockito.mock(Set.class);
		Mockito.when(validator.validate(copies)).thenReturn(violations);
		Mockito.when(violations.isEmpty()).thenReturn(false);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, librarianController.updateCopies(copies).getStatusCode());
	}

	@Test
	void updateCopiesFailure() {
		Copies copies = Mockito.mock(Copies.class);
		Mockito.when(librarianService.updateCopies(copies)).thenReturn(false);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, librarianController.updateCopies(copies).getStatusCode());
	}

}
