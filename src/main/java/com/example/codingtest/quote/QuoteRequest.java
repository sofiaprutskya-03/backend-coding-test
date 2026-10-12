package com.example.codingtest.quote;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record QuoteRequest(
        @NotBlank(message = "Author must not be empty")
        @Size(max = 200, message = "Author must not be longer than 200 characters")
        String author,

        @NotBlank(message = "Text must not be empty")
        @Size(max = 1000, message = "Text must not be longer than 1000 characters")
        String text
) {
}
