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

@Entity
@Table(name = "tbl_borrower")
public class Borrower {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cardNo")
	private Long cardNumber;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_book_loans", joinColumns = { @JoinColumn(name = "cardNo") }, inverseJoinColumns = {
			@JoinColumn(name = "branchId") })
	@JsonBackReference(value = "library_branch_borrower")
	private List<LibraryBranch> libraryBranches;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_book_loans", joinColumns = { @JoinColumn(name = "cardNo") }, inverseJoinColumns = {
			@JoinColumn(name = "bookId") })
	@JsonBackReference(value = "book_borrower")
	private List<Book> books;
	
	public Borrower() {
		
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<LibraryBranch> getLibraryBranches() {
		return libraryBranches;
	}

	public void setLibraryBranches(List<LibraryBranch> libraryBranches) {
		this.libraryBranches = libraryBranches;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}