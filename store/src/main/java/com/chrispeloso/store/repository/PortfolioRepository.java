package com.chrispeloso.store.repository;

import com.chrispeloso.store.model.PortfolioEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<PortfolioEntry, Long> {

}
