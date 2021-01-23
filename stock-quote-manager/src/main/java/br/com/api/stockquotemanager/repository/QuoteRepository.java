package br.com.api.stockquotemanager.repository;

import br.com.api.stockquotemanager.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query("select e from Quote e where e.stock.cod = ?1")
    List<Quote> findByCodStock(Long codStock);

    @Query("select e from Quote e where e.stock.id = ?1")
    List<Quote> findByIdStock(String idStock);
}
