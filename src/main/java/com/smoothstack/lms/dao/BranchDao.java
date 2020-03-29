package com.smoothstack.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.model.Branch;

@Repository
public interface BranchDao extends JpaRepository<Branch, Long> {

}
