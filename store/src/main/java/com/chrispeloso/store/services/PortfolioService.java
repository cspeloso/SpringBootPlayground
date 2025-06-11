package com.chrispeloso.store.services;

import com.chrispeloso.store.dto.PortfolioSummaryDTO;
import com.chrispeloso.store.model.PortfolioEntry;
import com.chrispeloso.store.model.QuoteResponse;
import com.chrispeloso.store.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<PortfolioSummaryDTO> getPortfolioSummary() {
        List<PortfolioEntry> entries = portfolioRepository.findAll();
        return entries.stream()
                .map(entry -> {
                    QuoteResponse quote = finnhubService.getQuote(entry.getSymbol()).block();
                    double price = quote != null ? quote.getC() : 0.0;
                    return new PortfolioSummaryDTO(entry.getSymbol(), entry.getQuantity(), price);
                })
                .collect(Collectors.toList());
    }

    public void addEntry(PortfolioEntry entry){
        portfolioRepository.save(entry);
    }
}
