package br.com.api.stockquotemanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stock")
@EqualsAndHashCode(of = "id")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod")
    private Long cod;

    @NotNull
    @Column(name = "id")
    private String id;

    @NotNull
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Quote> quotes = new ArrayList<>();

    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

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

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}