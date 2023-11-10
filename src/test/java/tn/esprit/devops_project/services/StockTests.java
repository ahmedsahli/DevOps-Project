package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StockTests {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddStock() {
        Stock stock = new Stock();
        stock.setTitle("Test Stock");

        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);

        Stock result = stockService.addStock(stock);

        Mockito.verify(stockRepository).save(stock);

        assertEquals("Test Stock", result.getTitle());
    }

    @Test
    public void testRetrieveStock() {
        Stock stock = new Stock();
        stock.setIdStock(1L);

        Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        Stock result = stockService.retrieveStock(1L);

        Mockito.verify(stockRepository).findById(1L);

        assertEquals(1L, result.getIdStock());
    }

    @Test
    public void testRetrieveStockNotFound() {
        Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> stockService.retrieveStock(1L));
    }

    @Test
    public void testRetrieveAllStock() {
        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock());
        stockList.add(new Stock());

        Mockito.when(stockRepository.findAll()).thenReturn(stockList);

        List<Stock> result = stockService.retrieveAllStock();

        Mockito.verify(stockRepository).findAll();

        assertEquals(2, result.size());
    }
}
