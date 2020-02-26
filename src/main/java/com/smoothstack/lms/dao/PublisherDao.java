package com.smoothstack.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.model.Publisher;

@Repository
public interface PublisherDao extends JpaRepository<Publisher, Long> {

}
