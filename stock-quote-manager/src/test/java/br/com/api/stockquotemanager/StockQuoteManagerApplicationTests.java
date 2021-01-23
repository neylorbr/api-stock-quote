package br.com.api.stockquotemanager;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import br.com.api.stockquotemanager.dto.QuoteDto;
import br.com.api.stockquotemanager.dto.StockDto;
import br.com.api.stockquotemanager.dto.StockDtoMapper;
import br.com.api.stockquotemanager.model.Quote;
import br.com.api.stockquotemanager.model.Stock;
import br.com.api.stockquotemanager.service.StockService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class StockQuoteManagerApplicationTests {

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void getAllStocks() {
		StockService stockServiceMock = mock(StockService.class);

		List<StockDto> list = new ArrayList<>();

		StockDto stock1 = new StockDto();
		stock1.setId("Teste");
		stock1.setDescription("Descrição do teste");

		QuoteDto quote1 = new QuoteDto();
		quote1.setDate(LocalDate.now());
		quote1.setValue(50.00F);
		quote1.setStockDto(stock1);

		stock1.setQuotes(Arrays.asList(quote1));

		StockDto stock2 = new StockDto();
		stock2.setId("Teste");
		stock2.setDescription("Descrição do teste");

		QuoteDto quote2 = new QuoteDto();
		quote2.setDate(LocalDate.now());
		quote2.setValue(50.00F);
		quote2.setStockDto(stock2);

		stock1.setQuotes(Arrays.asList(quote2));

		list.add(stock1);
		list.add(stock2);

		when(stockServiceMock.findAll()).thenReturn(list);
		Assert.assertEquals(stockServiceMock.findAll(), list);


	}


	}
