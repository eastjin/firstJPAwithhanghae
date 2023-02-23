package com.eastjin.firstboardproject.repository;

import com.eastjin.firstboardproject.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}