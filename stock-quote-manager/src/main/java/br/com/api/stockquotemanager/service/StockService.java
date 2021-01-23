package br.com.api.stockquotemanager.service;

import br.com.api.stockquotemanager.dto.QuoteDto;
import br.com.api.stockquotemanager.dto.StockDto;
import br.com.api.stockquotemanager.dto.StockDtoMapper;
import br.com.api.stockquotemanager.model.Quote;
import br.com.api.stockquotemanager.model.Stock;
import br.com.api.stockquotemanager.repository.StockRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StockService {

    protected final StockRepository repository;
    protected final StockDtoMapper mapper;

    public StockService(StockRepository repository, StockDtoMapper mapper){
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<StockDto> findAll(){
        return repository.findAll().stream().map(mapper::modelToDto).collect(Collectors.toList());
    }

    public StockDto findById(Long cod){
        return repository.findById(cod).map(mapper::modelToDto).orElse(null);
    }

    public StockDto findByIdentify(String identify){
        return repository.findByIdentify(identify).map(mapper::modelToDto).orElse(null);
    }

    public List<StockDto> saveAll(List<StockDto> dtos){
        List<StockDto> dtosSaved = new ArrayList<>();
        dtos.stream().forEach(d ->dtosSaved.add(save(d)));
        return dtosSaved;
    }

    public StockDto save(StockDto dto){
        Stock model = new Stock();
        if(dto.getId() != null){
            model = repository.findByIdentify(dto.getId()).orElse(new Stock());
        }
        mapper.dtoToModel(dto, model);
        Stock modelSaved = repository.save(model);
        return mapper.modelToDto(modelSaved);
    }

}
