package com.chrispeloso.store.services;

import com.chrispeloso.store.model.PortfolioEntry;
import com.chrispeloso.store.model.PortfolioSummaryResponse;
import com.chrispeloso.store.model.StockHolding;
import com.chrispeloso.store.repository.PortfolioRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioService {

    //  parameters
    private final PortfolioRepository portfolioRepository;
    private final FinnhubService finnhubService;

    //  constructor
    public PortfolioService(PortfolioRepository pr, FinnhubService fs) {
        this.portfolioRepository = pr;
        this.finnhubService = fs;
    }

    //  gets a summary of the user's portfolio positions.
    public Mono<PortfolioSummaryResponse> getPortfolioSummary() {
        List<PortfolioEntry> entries = portfolioRepository.findAll();
        List<Mono<StockHolding>> holdingMonos = new ArrayList<>();

        for (PortfolioEntry entry : entries) {
            Mono<StockHolding> holdingMono = finnhubService.getQuote(entry.getSymbol())
                    .map(quote -> new StockHolding(
                            entry.getSymbol(),
                            entry.getQuantity(),
                            quote.getC(),
                            quote.getC() * entry.getQuantity()
                    ));
            holdingMonos.add(holdingMono);
        }

        return Mono.zip(holdingMonos, results -> {
            List<StockHolding> holdings = new ArrayList<>();
            double totalValue = 0;
            for (Object result : results) {
                StockHolding holding = (StockHolding) result;
                totalValue += holding.getValue();
                holdings.add(holding);
            }
            return new PortfolioSummaryResponse(holdings, totalValue);
        });
    }

    public void addEntry(PortfolioEntry entry){
        portfolioRepository.save(entry);
    }
}
