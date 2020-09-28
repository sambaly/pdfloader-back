package com.example.demo.controllers;

import com.example.demo.domain.SearchResult;
import com.example.demo.repositories.SearchResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("results")
public class SearchResultController {

    @Autowired
    private SearchResultRepository searchResultRepository;

    @GetMapping("/all-results")
    public List<SearchResult> getAllResults() {
        return this.searchResultRepository.findAll();
    }

}
