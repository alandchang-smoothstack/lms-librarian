package com.smoothstack.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.entity.Borrower;

@Repository
public interface BorrowerDao extends JpaRepository<Borrower, Long> {

}
