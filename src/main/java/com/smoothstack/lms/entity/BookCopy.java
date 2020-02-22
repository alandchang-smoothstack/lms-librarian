package com.smoothstack.lms.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book_copies")
public class BookCopy {

	@EmbeddedId
	private BookCopyId id;

	@Column(name = "noOfCopies")
	private Integer amount;
	
	public BookCopy() {
		
	}

	public BookCopyId getId() {
		return id;
	}

	public void setId(BookCopyId id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
