package com.smoothstack.lms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class BookCopyId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "bookId")
	@NotNull
	private Long bookId;

	@Column(name = "branchId")
	@NotNull
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((libraryBranchId == null) ? 0 : libraryBranchId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookCopyId other = (BookCopyId) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (libraryBranchId == null) {
			if (other.libraryBranchId != null)
				return false;
		} else if (!libraryBranchId.equals(other.libraryBranchId))
			return false;
		return true;
	}

}