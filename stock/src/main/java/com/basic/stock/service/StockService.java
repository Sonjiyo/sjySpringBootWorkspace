package com.basic.stock.service;

import com.basic.stock.entity.Stock;
import com.basic.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StockService {
    private final StockRepository repository;

    //@Transactional
    public synchronized void decreaseStock(Long id, Long quantity){

        // stock 조회
        Stock stock = repository.findById(id).orElseThrow();  // null 이면 에러 출력
        // 재고 감소
        stock.decreaseStock(quantity);
        // 바로 갱신된 값을 db 저장
        repository.saveAndFlush(stock);

    }

}
