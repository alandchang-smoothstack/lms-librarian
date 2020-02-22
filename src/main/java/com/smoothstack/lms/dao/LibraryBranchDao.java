package com.smoothstack.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.entity.LibraryBranch;

@Repository
public interface LibraryBranchDao extends JpaRepository<LibraryBranch, Long> {

}
