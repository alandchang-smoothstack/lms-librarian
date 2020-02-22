package com.smoothstack.lms.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_library_branch")
public class LibraryBranch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branchId")
	private Long id;

	@Column(name = "branchName")
	private String name;

	@Column(name = "branchAddress")
	private String address;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_book_loans", joinColumns = { @JoinColumn(name = "branchId") }, inverseJoinColumns = {
			@JoinColumn(name = "bookId") })
	@JsonBackReference(value = "book_library_branch")
	private List<Book> books;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_book_loans", joinColumns = { @JoinColumn(name = "branchId") }, inverseJoinColumns = {
			@JoinColumn(name = "cardNo") })
	@JsonManagedReference(value = "library_branch_borrower")
	private List<Borrower> borrowers;

	public LibraryBranch() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Borrower> getBorrowers() {
		return borrowers;
	}

	public void setBorrowers(List<Borrower> borrowers) {
		this.borrowers = borrowers;
	}

}
