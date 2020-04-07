package com.smoothstack.lms.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.smoothstack.lms.log.LoggerWrapper;
import com.smoothstack.lms.model.Book;
import com.smoothstack.lms.model.Branch;
import com.smoothstack.lms.model.Copies;
import com.smoothstack.lms.service.LibrarianService;

@RequestMapping("/librarian")
@RestController
public class LibrarianController {

	@Autowired
	private LibrarianService librarianService;

	@Autowired
	Validator validator;

	@GetMapping(path = "/librarybranches")
	@Produces({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Branch>> getBranches() {
		List<Branch> libraryBranches = librarianService.getBranches();
		if (libraryBranches.isEmpty()) {
			LoggerWrapper.log.info("Get library branches: " + Response.Status.NOT_FOUND.getStatusCode());
			return ResponseEntity.notFound().build();
		}
		LoggerWrapper.log.info("getBranches() Status Code: " + Response.Status.OK.getStatusCode());
		return ResponseEntity.ok(libraryBranches);
	}

	@GetMapping(path = "/librarybranches/{id}")
	@Produces({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Optional<Branch>> getBranchById(@PathVariable long id) {
		Optional<Branch> libraryBranch = librarianService.getBranchById(id);
		if (!libraryBranch.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(libraryBranch);
	}

	@PutMapping(path = "/librarybranches")
	@Consumes({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> updateBranch(@RequestBody Branch libraryBranch) {
		if (!validator.validate(libraryBranch).isEmpty() || !librarianService.updateBranch(libraryBranch)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = "/books")
	@Produces({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> books = librarianService.getBooks();
		if (books.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(books);
	}

	@GetMapping(path = "/bookcopies/books/{bookId}/librarybranches/{libraryBranchId}")
	@Produces({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Optional<Copies>> getCopiesById(@PathVariable long bookId,
			@PathVariable long libraryBranchId) {
		Optional<Copies> copies = librarianService.getCopiesById(bookId, libraryBranchId);
		if (!copies.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(copies);
	}

	@PostMapping(path = "/bookcopies")
	@Consumes({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> addCopies(@RequestBody Copies copies) {
		if (!validator.validate(copies).isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		librarianService.addCopies(copies);
		return ResponseEntity
				.created(UriComponentsBuilder
						.fromUriString("/librarian/bookcopies/books/{bookId}/librarybranches/{libraryBranchId}")
						.buildAndExpand(copies.getBook().getBookId(), copies.getBranch().getBranchId()).toUri())
				.build();
	}

	@PutMapping(path = "/bookcopies")
	@Consumes({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> updateCopies(@RequestBody Copies copies) {
		if (!validator.validate(copies).isEmpty() || !librarianService.updateCopies(copies)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.noContent().build();
	}

}
