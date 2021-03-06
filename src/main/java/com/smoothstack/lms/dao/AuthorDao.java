package com.smoothstack.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.model.Author;

@Repository
public interface AuthorDao extends JpaRepository<Author, Long> {

}
