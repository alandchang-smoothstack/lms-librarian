package com.smoothstack.lms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
	public boolean updateLibraryBranch(LibraryBranch libraryBranch) throws SQLException {
		if (!libraryBranchDao.existsById(libraryBranch.getId())) {
			return false;
		}
		libraryBranchDao.save(libraryBranch);
		return true;
	}

	public List<Book> getBooks() throws SQLException {
		return bookDao.findAll();
	}

	public Optional<BookCopy> getBookCopyById(long bookId, long libraryBranchId) throws SQLException {
		return bookCopyDao.findById(new BookCopyId(bookId, libraryBranchId));
	}

	@Transactional
	public void addBookCopy(BookCopy bookCopy) throws SQLException {
		bookCopyDao.save(bookCopy);
	}

	@Transactional
	public boolean updateBookCopy(BookCopy bookCopy) throws SQLException {
		if (!bookCopyDao.existsById(bookCopy.getId())) {
			return false;
		}
		bookCopyDao.save(bookCopy);
		return true;
	}

}