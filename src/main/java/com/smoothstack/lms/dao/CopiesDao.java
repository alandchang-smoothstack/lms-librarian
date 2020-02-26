package com.smoothstack.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.model.BookBranch;
import com.smoothstack.lms.model.Copies;

@Repository
public interface CopiesDao extends JpaRepository<Copies, BookBranch> {

}
