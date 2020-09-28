package com.example.demo.repositories;

import com.example.demo.domain.SearchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchResultRepository extends JpaRepository<SearchResult, Long> {
}
