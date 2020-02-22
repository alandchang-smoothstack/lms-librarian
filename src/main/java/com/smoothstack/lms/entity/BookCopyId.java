package com.smoothstack.lms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BookCopyId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "bookId")
	private Long bookId;

	@Column(name = "branchId")
	private Long libraryBranchId;

	public BookCopyId() {

	}

	public BookCopyId(Long bookId, Long libraryBranchId) {
		this.setBookId(bookId);
		this.setLibraryBranchId(libraryBranchId);
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getLibraryBranchId() {
		return libraryBranchId;
	}

	public void setLibraryBranchId(Long libraryBranchId) {
		this.libraryBranchId = libraryBranchId;
	}

}