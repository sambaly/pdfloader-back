package com.example.demo.repositories;

import com.example.demo.domain.ArticleWriter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleWriterRepository extends JpaRepository<ArticleWriter, Long> {

}
