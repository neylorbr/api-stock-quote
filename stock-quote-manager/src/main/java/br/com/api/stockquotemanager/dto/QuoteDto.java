package br.com.api.stockquotemanager.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class QuoteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate date;
    private Float value;
    private StockDto stockDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public StockDto getStockDto() {
        return stockDto;
    }

    public void setStockDto(StockDto stockDto) {
        this.stockDto = stockDto;
    }
}
