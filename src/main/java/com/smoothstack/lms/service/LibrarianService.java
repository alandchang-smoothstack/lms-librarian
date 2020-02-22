package com.smoothstack.lms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.lms.dao.BookCopyDao;
import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.dao.LibraryBranchDao;
import com.smoothstack.lms.entity.Book;
import com.smoothstack.lms.entity.BookCopy;
import com.smoothstack.lms.entity.BookCopyId;
import com.smoothstack.lms.entity.LibraryBranch;

@Service
public class LibrarianService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	private LibraryBranchDao libraryBranchDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private BookCopyDao bookCopyDao;

	public List<LibraryBranch> getLibraryBranches() throws SQLException {
		return libraryBranchDao.findAll();
	}

	public Optional<LibraryBranch> getLibraryBranchById(long id) throws SQLException {
		return libraryBranchDao.findById(id);
	}

	@Transactional
	public boolean updateLibraryBranch(LibraryBranch newLibraryBranch) throws SQLException {
		try {
			LibraryBranch libraryBranch = entityManager.find(LibraryBranch.class, newLibraryBranch.getId());
			if (libraryBranch == null) {
				return false;
			}
			libraryBranch.setName(newLibraryBranch.getName());
			libraryBranch.setAddress(newLibraryBranch.getAddress());
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Book> getBooks() throws SQLException {
		return bookDao.findAll();
	}

	public Optional<BookCopy> getBookCopyById(long bookId, long libraryBranchId) throws SQLException {
		return bookCopyDao.findById(new BookCopyId(bookId, libraryBranchId));
	}

	@Transactional
	public boolean addBookCopy(BookCopy bookCopy) throws SQLException {
		try {
			if (entityManager.find(BookCopy.class,
					new BookCopyId(bookCopy.getId().getBookId(), bookCopy.getId().getLibraryBranchId())) != null) {
				return false;
			}
			entityManager.persist(bookCopy);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public boolean updateBookCopy(BookCopy newBookCopy) throws SQLException {
		try {
			BookCopy bookCopy = entityManager.find(BookCopy.class,
					new BookCopyId(newBookCopy.getId().getBookId(), newBookCopy.getId().getLibraryBranchId()));
			if (bookCopy == null) {
				return false;
			}
			bookCopy.setAmount(newBookCopy.getAmount());
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

}