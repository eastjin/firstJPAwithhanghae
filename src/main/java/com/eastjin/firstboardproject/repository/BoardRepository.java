package com.eastjin.firstboardproject.repository;
import com.eastjin.firstboardproject.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //내림차순 정렬
    List<Board> findAllByOrderByModifiedAtDesc();

}