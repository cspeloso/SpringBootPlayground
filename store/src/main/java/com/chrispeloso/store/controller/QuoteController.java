package com.chrispeloso.store.controller;

import com.chrispeloso.store.model.QuoteResponse;
import com.chrispeloso.store.services.FinnhubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class QuoteController {

    private final FinnhubService finnhubService;

    public QuoteController(FinnhubService finnhubService) {
        this.finnhubService = finnhubService;
    }

    @GetMapping("/quote/{symbol}")
    public Mono<QuoteResponse> getQuote(@PathVariable String symbol) {
        return finnhubService.getQuote(symbol);
    }

}
