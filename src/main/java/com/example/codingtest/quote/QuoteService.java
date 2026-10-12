package com.example.codingtest.quote;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Transactional
    public QuoteResponse create(QuoteRequest request) {
        Quote quote = quoteRepository.save(new Quote(request.author(), request.text()));
        return QuoteResponse.from(quote);
    }

    @Transactional(readOnly = true)
    public QuoteResponse getById(Long id) {
        return quoteRepository.findById(id)
                .map(QuoteResponse::from)
                .orElseThrow(() -> new QuoteNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public Page<QuoteResponse> getAll(String author, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        if (author == null || author.isBlank()) {
            return quoteRepository.findAll(pageRequest).map(QuoteResponse::from);
        }

        return quoteRepository.findByAuthorContainingIgnoreCase(author.trim(), pageRequest)
                .map(QuoteResponse::from);
    }

    @Transactional
    public QuoteResponse update(Long id, QuoteRequest request) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException(id));

        quote.update(request.author(), request.text());
        return QuoteResponse.from(quote);
    }

    @Transactional
    public void delete(Long id) {
        if (!quoteRepository.existsById(id)) {
            throw new QuoteNotFoundException(id);
        }

        quoteRepository.deleteById(id);
    }
}
