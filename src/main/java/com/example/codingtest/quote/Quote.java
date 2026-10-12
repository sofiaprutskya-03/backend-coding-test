package com.example.codingtest.quote;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String author;

    @Column(nullable = false, length = 1000)
    private String text;

    protected Quote() {
    }

    public Quote(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public void update(String author, String text) {
        this.author = author;
        this.text = text;
    }
}
