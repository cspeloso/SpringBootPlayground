package com.chrispeloso.store.services;

import com.chrispeloso.store.model.QuoteResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FinnhubService {

    private final WebClient webClient;

    public FinnhubService(WebClient finnhubWebClient) {
        this.webClient = finnhubWebClient;
    }

    public Mono<QuoteResponse> getQuote(String symbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/quote")
                        .queryParam("symbol", symbol)
                        .build())
                .retrieve()
                .bodyToMono(QuoteResponse.class);
    }

}
