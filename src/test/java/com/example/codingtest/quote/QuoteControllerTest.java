package com.example.codingtest.quote;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class QuoteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuoteRepository quoteRepository;

    @BeforeEach
    void setUp() {
        quoteRepository.deleteAll();
    }

    @Test
    void createsAndReadsQuote() throws Exception {
        mockMvc.perform(post("/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "author": "Lesya Ukrainka",
                                  "text": "I have in my heart that which does not die."
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber());

        mockMvc.perform(get("/quotes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].author").value("Lesya Ukrainka"));
    }

    @Test
    void filtersQuotesByAuthor() throws Exception {
        quoteRepository.save(new Quote("Author One", "First text"));
        quoteRepository.save(new Quote("Author Two", "Second text"));

        mockMvc.perform(get("/quotes").param("author", "two"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].author").value("Author Two"));
    }

    @Test
    void updatesQuote() throws Exception {
        Quote quote = quoteRepository.save(new Quote("Old Author", "Old text"));

        mockMvc.perform(put("/quotes/{id}", quote.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "author": "New Author",
                                  "text": "New text"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("New Author"))
                .andExpect(jsonPath("$.text").value("New text"));
    }

    @Test
    void deletesQuote() throws Exception {
        Quote quote = quoteRepository.save(new Quote("Author", "Text"));

        mockMvc.perform(delete("/quotes/{id}", quote.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/quotes/{id}", quote.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void rejectsInvalidQuote() throws Exception {
        mockMvc.perform(post("/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "author": "",
                                  "text": ""
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.author").exists())
                .andExpect(jsonPath("$.details.text").exists());
    }
}
