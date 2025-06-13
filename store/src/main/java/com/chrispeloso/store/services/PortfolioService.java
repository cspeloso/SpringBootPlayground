package com.chrispeloso.store.services;

import com.chrispeloso.store.dto.PortfolioSummaryDTO;
import com.chrispeloso.store.model.PortfolioEntry;
import com.chrispeloso.store.model.QuoteResponse;
import com.chrispeloso.store.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public boolean addEntry(PortfolioEntry entry) {
        Optional<PortfolioEntry> optionalEntry = portfolioRepository.findBySymbol(entry.getSymbol());

        //  if we already have this stock in our portfolio, get that row and update its quantity.
        if (optionalEntry.isPresent()) {
            var foundEntry = optionalEntry.get();

            foundEntry.setQuantity(foundEntry.getQuantity() + entry.getQuantity());

            portfolioRepository.save(foundEntry);
        }
        //  if we don't have the stock in our portfolio already, then we'll add a new row with this quantity.
        else {
            portfolioRepository.save(entry);
        }

        return true;
    }

    public boolean sellShares(String symbol, int quantity) {

        Optional<PortfolioEntry> optionalEntry = portfolioRepository.findBySymbol(symbol);

        //  check if the entry with this symbol can be found...
        if (optionalEntry.isPresent()) {
            PortfolioEntry entry = optionalEntry.get();

            //  if the quantity provided is greater than or equal to the quantity saved in db, delete
            if (quantity >= entry.getQuantity())
                portfolioRepository.delete(entry);
                //  otherwise, set the quantity to current db quantity - quantity provided.
            else {
                entry.setQuantity(entry.getQuantity() - quantity);
                portfolioRepository.save(entry);
            }

            return true;
        }

        //  if the request symbol can't be found, return false.
        return false;
    }

    public boolean sellAllShares(String symbol) {
        Optional<PortfolioEntry> optionalEntry = portfolioRepository.findBySymbol(symbol);

        if (optionalEntry.isPresent()) {
            PortfolioEntry entry = optionalEntry.get();

            portfolioRepository.delete(entry);

            //  return true if the entry was successfully deleted...
            return true;
        }

        //  return false if the entry couldn't be found in the database...
        return false;
    }

}
