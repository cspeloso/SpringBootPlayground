package com.chrispeloso.store.repository;

import com.chrispeloso.store.model.PortfolioEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<PortfolioEntry, Long> {

    Optional<PortfolioEntry> findBySymbol(String symbol);

}
