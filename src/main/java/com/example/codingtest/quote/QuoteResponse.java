package com.example.codingtest.quote;

public record QuoteResponse(Long id, String author, String text) {
    public static QuoteResponse from(Quote quote) {
        return new QuoteResponse(quote.getId(), quote.getAuthor(), quote.getText());
    }
}
