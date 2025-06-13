package com.chrispeloso.store.controller;

import com.chrispeloso.store.dto.PortfolioSummaryDTO;
import com.chrispeloso.store.model.PortfolioEntry;
import com.chrispeloso.store.model.SellRequest;
import com.chrispeloso.store.services.PortfolioService;
import net.bytebuddy.pool.TypePool;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    //#region Parameters
    private final PortfolioService service;
    //#endregion

    //#region Constructors
    public PortfolioController(PortfolioService service) {
        this.service = service;
    }
    //#endregion

    //#region API Endpoints
    /// Gets the current portfolio of stocks.
    @GetMapping("/summary")
    public List<PortfolioSummaryDTO> getSummary() {
        return service.getPortfolioSummary();
    }

    /// Purchases the quantity provided of the stock provided.
    @PostMapping("/buy")
    public ResponseEntity<?> addEntry(@RequestParam String symbol, @RequestParam int quantity) {

        //  guard
        symbolIsValid(symbol);
        quantityIsValid(quantity);

        var entry = new PortfolioEntry(symbol, quantity);
        boolean success = service.addEntry(entry);
        return success
                ? ResponseEntity.ok(service.getPortfolioSummary())
                : ResponseEntity.badRequest().body("Purchase failed: Invalid symbol.");
    }

    /// Will sell the quantity provided of shares of symbol provided.
    @PostMapping("/sell-shares")
    public ResponseEntity<?> sellShares(@RequestBody SellRequest sellRequest) {

        System.out.println("Received Sell Request: " + sellRequest);

        symbolIsValid(sellRequest.getSymbol());
        quantityIsValid(sellRequest.getQuantity());

        boolean success = service.sellShares(sellRequest.getSymbol(), sellRequest.getQuantity());

        return success
                ? ResponseEntity.ok(service.getPortfolioSummary())
                : ResponseEntity.badRequest().body("Sell failed: invalid symbol or insufficient shares.");
    }

    /// Will sell all shares of a certain type, effectively a DELETE.
    @PostMapping("/sell-all-shares")
    public ResponseEntity<?> sellAllShares(@RequestParam String symbol) {

        System.out.println("Received Sell All Request: " + symbol);

        symbolIsValid(symbol);

        boolean success = service.sellAllShares(symbol);

        return success
                ? ResponseEntity.ok(service.getPortfolioSummary())
                : ResponseEntity.badRequest().body("Sell failed: Invalid symbol or insufficient shares.");
    }
    //#endregion

    //#region Guards

    /// Guard against blank/empty/null symbols.
    private void symbolIsValid(String symbol) {
        if (symbol.isBlank())
            throw new IllegalArgumentException("Symbol must be valid.");
    }

    /// Guard against invalid quantities
    private void quantityIsValid(int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be greater than 0.");
    }
    //#endregion
}
