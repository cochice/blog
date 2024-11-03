package com.marvin.springbootdeveloper1.repository;

import com.marvin.springbootdeveloper1.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
