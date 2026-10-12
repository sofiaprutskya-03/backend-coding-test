package com.example.codingtest.quote;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Page<Quote> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
}
