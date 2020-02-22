package com.smoothstack.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.entity.BookCopy;
import com.smoothstack.lms.entity.BookCopyId;

@Repository
public interface BookCopyDao extends JpaRepository<BookCopy, BookCopyId> {

}
