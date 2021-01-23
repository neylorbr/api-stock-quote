package br.com.api.stockquotemanager.service;

import br.com.api.stockquotemanager.dto.QuoteDto;
import br.com.api.stockquotemanager.dto.QuoteDtoMapper;
import br.com.api.stockquotemanager.dto.StockDtoMapper;
import br.com.api.stockquotemanager.exceptions.ResourceNotFoundException;
import br.com.api.stockquotemanager.model.Quote;
import br.com.api.stockquotemanager.repository.QuoteRepository;
import br.com.api.stockquotemanager.repository.StockRepository;
import br.com.api.stockquotemanager.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuoteService {

    protected final QuoteRepository repository;
    protected final QuoteDtoMapper mapper;

    public QuoteService(QuoteRepository repository, QuoteDtoMapper mapper){
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<QuoteDto> findAll(){
        return repository.findAll().stream().map(mapper::modelToDto).collect(Collectors.toList());
    }

    public QuoteDto findById(Long id){
        return repository.findById(id).map(mapper::modelToDto).orElse(null);
    }

    public List<QuoteDto> findByCodStock(Long codStock){
        return repository.findByCodStock(codStock).stream().map(mapper::modelToDto).collect(Collectors.toList());
    }

    public List<QuoteDto> findByIdStock(String idStock){
        return repository.findByIdStock(idStock).stream().map(mapper::modelToDto).collect(Collectors.toList());
    }

    public QuoteDto save(QuoteDto dto){
        Quote model = new Quote();
        if(dto.getId() != null){
            model = repository.findById(dto.getId()).orElse(new Quote());
        }
        mapper.dtoToModel(dto, model);
        Quote modelSaved = repository.save(model);
        return mapper.modelToDto(modelSaved);
    }

    public Boolean verifiRequideField(QuoteDto dto){
        return dto.getValue() != null && dto.getDate() != null;
    }
}
