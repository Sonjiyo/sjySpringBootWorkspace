package com.basic.stock.transcation;

import com.basic.stock.service.StockService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransactionStockService {

    private final StockService stockService;

    public void decrease(Long id, Long quantity){
        startTranscation(); // tx.begin(()
        stockService.decreaseStock(id, quantity);

        endTranscation();// tx.commit()
    }

    private void startTranscation(){
        System.out.println(" 트랜젝션 시작");
    }
    private void endTranscation(){
        System.out.println(" 트랜젝션 끝");
    }

}
