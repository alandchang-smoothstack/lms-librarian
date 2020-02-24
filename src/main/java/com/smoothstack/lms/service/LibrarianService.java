package com.smoothstack.lms.service;

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

	public List<LibraryBranch> getLibraryBranches() throws Exception {
		return libraryBranchDao.findAll();
	}

	public Optional<LibraryBranch> getLibraryBranchById(long id) throws Exception {
		return libraryBranchDao.findById(id);
	}

	@Transactional
	public boolean updateLibraryBranch(LibraryBranch libraryBranch) throws Exception {
		if (!libraryBranchDao.existsById(libraryBranch.getId())) {
			return false;
		}
		libraryBranchDao.save(libraryBranch);
		return true;
	}

	public List<Book> getBooks() throws Exception {
		return bookDao.findAll();
	}

	public Optional<BookCopy> getBookCopyById(long bookId, long libraryBranchId) throws Exception {
		return bookCopyDao.findById(new BookCopyId(bookId, libraryBranchId));
	}

	@Transactional
	public void addBookCopy(BookCopy bookCopy) throws Exception {
		bookCopyDao.save(bookCopy);
	}

	@Transactional
	public boolean updateBookCopy(BookCopy bookCopy) throws Exception {
		if (!bookCopyDao.existsById(bookCopy.getId())) {
			return false;
		}
		bookCopyDao.save(bookCopy);
		return true;
	}

}