package edu.wctc.stockpurchase.controller;

import edu.wctc.stockpurchase.entity.StockPurchase;
import edu.wctc.stockpurchase.exception.ResourceNotFoundException;
import edu.wctc.stockpurchase.service.StockPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/stockpurchases")
public class StockPurchaseController {

    private StockPurchaseService service;

    @Autowired
    public StockPurchaseController(StockPurchaseService sps) {
        this.service = sps;
    }

    @GetMapping
    public List<StockPurchase> getPurchases() {
        return service.getAllStockPurchases();
    }

    @PostMapping("")
    public StockPurchase NewPurchase(){
        StockPurchase s = new StockPurchase();
        s.setPurchase_date(new Date());
        s.setSymbol("ZAC");
        s.setPrice_per_share(69.69);
        s.setShares(69);
        return service.save(s);
    }

    @DeleteMapping("/{stockPurchaseId}")
    public String deleteStockPurchase(@PathVariable String stockPurchaseId) {
        try {
            int id = Integer.parseInt(stockPurchaseId);
            service.delete(id);
            return "Purchase deleted: ID " + stockPurchaseId;
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Stock purchase ID must be a number", e);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    e.getMessage(), e);
        }
    }

    @PutMapping("")
    public StockPurchase PutPurchase(){
        StockPurchase s = service.getStock("11");
        s.setPurchase_date(new Date());
        s.setPrice_per_share(17.02);
        s.setShares(50);
        return service.save(s);
    }

    @GetMapping("/{stockPurchaseId}")
    public StockPurchase getStockPurchase(@PathVariable String stockPurchaseId) {
        try {
            int id = Integer.parseInt(stockPurchaseId);
            return service.getStockPurchase(id);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "purchase ID must be a number", e);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    e.getMessage(), e);
        }
    }

}
