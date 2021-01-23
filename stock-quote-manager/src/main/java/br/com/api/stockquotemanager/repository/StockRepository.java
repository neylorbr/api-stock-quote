package br.com.api.stockquotemanager.repository;

import br.com.api.stockquotemanager.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("select e from Stock e where e.id = ?1")
    Optional<Stock> findByIdentify(String identify);

}
