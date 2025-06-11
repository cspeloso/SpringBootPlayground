package com.chrispeloso.store.controller;

import com.chrispeloso.store.model.PortfolioEntry;
import com.chrispeloso.store.model.PortfolioSummaryResponse;
import com.chrispeloso.store.services.PortfolioService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final PortfolioService service;

    public PortfolioController(PortfolioService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public Mono<PortfolioSummaryResponse> getSummary() {
        return service.getPortfolioSummary();
    }

    @PostMapping("/add")
    public void addEntry(@RequestParam String symbol, @RequestParam int quantity) {
        var entry = new PortfolioEntry(symbol, quantity);
        service.addEntry(entry);
    }

}
