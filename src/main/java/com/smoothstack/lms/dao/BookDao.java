package com.smoothstack.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.model.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {

}
