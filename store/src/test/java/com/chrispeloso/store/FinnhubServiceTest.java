package com.chrispeloso.store;

import com.chrispeloso.store.model.QuoteResponse;
import com.chrispeloso.store.services.FinnhubService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FinnhubServiceTest {

    @Autowired
    private FinnhubService service;

    @Test
    void testGetQuote() {
        QuoteResponse quote = service.getQuote("AAPL").block();

        //  validate response is not null
        assertNotNull(quote);

        //  validate stock price is not 0
        assertTrue(quote.getC() > 0);
    }
}
