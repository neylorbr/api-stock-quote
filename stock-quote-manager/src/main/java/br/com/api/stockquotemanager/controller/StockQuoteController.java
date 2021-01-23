package br.com.api.stockquotemanager.controller;

import br.com.api.stockquotemanager.dto.StockDto;
import br.com.api.stockquotemanager.exceptions.ResourceNotFoundException;
import br.com.api.stockquotemanager.model.Stock;
import br.com.api.stockquotemanager.service.QuoteService;
import br.com.api.stockquotemanager.service.StockService;
import br.com.api.stockquotemanager.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/stockQuote")
public class StockQuoteController {

    private StockService stockService;
    private QuoteService quoteService;

    public StockQuoteController(StockService stockService, QuoteService quoteService){
        this.stockService = stockService;
        this.quoteService = quoteService;
    }

    @GetMapping()
    public ResponseEntity<List<StockDto>> findAll(){
        List<StockDto> retorno = stockService.findAll();
        retorno.stream().forEach(r -> findQuotes(r));
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @GetMapping(value = "/{identify}")
    public ResponseEntity<StockDto> findById(@PathVariable("identify") String identify) throws ResourceNotFoundException {
        StockDto retorno = stockService.findByIdentify(identify);
        if(retorno == null){
            throw new ResourceNotFoundException(Messages.ERROR_STOCK_QUOTE_NOT_FOUND);
        }else{
            this.findQuotes(retorno);
        }
        return new ResponseEntity<>(retorno, retorno == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<List<StockDto>> save(@RequestBody @Valid List<StockDto> dtos) throws ResourceNotFoundException  {
        if(dtos != null && !dtos.isEmpty()){
            dtos.stream().forEach(d ->{
                if(d.getId() == null){
                    try {
                        throw new ResourceNotFoundException(Messages.ERRO_REQUIRED_ID_STOCK);
                    } catch (ResourceNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if(!d.getQuotes().isEmpty()){
                    d.getQuotes().stream().forEach(q ->{
                        if(!quoteService.verifiRequideField(q)){
                            try {
                                throw new ResourceNotFoundException(Messages.ERRO_REQUIRED_ID_STOCK);
                            } catch (ResourceNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
            List<StockDto> dtosSaved = stockService.saveAll(dtos);
            if(dtosSaved == null){
                throw new ResourceNotFoundException(Messages.ERRO_NOT_SAVE);
            }
            return new ResponseEntity<>(dtosSaved, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    private void findQuotes(StockDto dto){
        if(dto.getQuotes() == null || dto.getQuotes().isEmpty()) {
            dto.setQuotes(quoteService.findByIdStock(dto.getId()));
        }
    }
}
