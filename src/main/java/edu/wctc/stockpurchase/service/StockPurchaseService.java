package edu.wctc.stockpurchase.service;

import edu.wctc.stockpurchase.entity.StockPurchase;

import edu.wctc.stockpurchase.exception.ResourceNotFoundException;
import edu.wctc.stockpurchase.repo.StockPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StockPurchaseService {
    private StockPurchaseRepository repo;

    @Autowired
    public StockPurchaseService(StockPurchaseRepository spr) {
        this.repo = spr;
    }

    public StockPurchase save(StockPurchase purchase) {
        return repo.save(purchase);
    }

    public List<StockPurchase> getAllStockPurchases() {
        List<StockPurchase> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }

    public void delete(int id) throws ResourceNotFoundException {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new ResourceNotFoundException("StockPurchase", "id", id);
        }
    }

    public StockPurchase getStockPurchase(int id) throws ResourceNotFoundException {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockPurchase", "id", id));
    }

    public StockPurchase getStock(String id){
        return getStock(Integer.parseInt(id));
    }

    public StockPurchase getStock(int id){
        return repo.findById(id).orElse(null);
    }
    public StockPurchase update(StockPurchase stockPurchase){
        if (repo.existsById(stockPurchase.getId())) {
            return repo.save(stockPurchase);
        } else {
            return null;
        }
    }

}
