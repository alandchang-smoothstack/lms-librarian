package com.smoothstack.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.entity.Genre;

@Repository
public interface GenreDao extends JpaRepository<Genre, Long> {

}
