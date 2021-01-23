package br.com.api.stockquotemanager.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
public class StockDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String description;
    private List<QuoteDto> quotes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuoteDto> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<QuoteDto> quotes) {
        this.quotes = quotes;
    }
}
