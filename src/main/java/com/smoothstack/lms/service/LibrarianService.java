package com.smoothstack.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.dao.BranchDao;
import com.smoothstack.lms.dao.CopiesDao;
import com.smoothstack.lms.model.Book;
import com.smoothstack.lms.model.BookBranch;
import com.smoothstack.lms.model.Branch;
import com.smoothstack.lms.model.Copies;

@Service
public class LibrarianService {

	@Autowired
	private BranchDao libraryBranchDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private CopiesDao copiesDao;

	public List<Branch> getBranches() {
		return libraryBranchDao.findAll();
	}

	public Optional<Branch> getBranchById(long id) {
		return libraryBranchDao.findById(id);
	}

	@Transactional
	public boolean updateBranch(Branch libraryBranch) {
		if (!libraryBranchDao.existsById(libraryBranch.getBranchId())) {
			return false;
		}
		libraryBranchDao.save(libraryBranch);
		return true;
	}

	public List<Book> getBooks() {
		return bookDao.findAll();
	}

	public Optional<Copies> getCopiesById(long bookId, long branchId) {
		return copiesDao.findById(new BookBranch(bookId, branchId));
	}

	@Transactional
	public void addCopies(Copies copies) {
		copiesDao.save(copies);
	}

	@Transactional
	public boolean updateCopies(Copies copies) {
		if (!copiesDao.existsById(new BookBranch(copies.getBook().getBookId(), copies.getBranch().getBranchId()))) {
			return false;
		}
		copiesDao.save(copies);
		return true;
	}

}