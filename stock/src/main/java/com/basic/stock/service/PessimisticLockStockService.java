package com.basic.stock.service;

import com.basic.stock.entity.Stock;
import com.basic.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 데이터, 테이블 자체 락
// 한 쓰레드가 데이터, 테이블 점유를 하면 다른 쓰레드는 건드리지 못함
// 데이터를 빈번하게 중복해서 건드릴 때 사용

@Service
@RequiredArgsConstructor
public class PessimisticLockStockService {
    private final StockRepository repository;

    @Transactional
    public Long decreaseStock(Long id, Long quantity){

        // stock 조회
        Stock stock = repository.findByIdWithPessimisstickLock(id);
        // 재고 감소
        stock.decreaseStock(quantity);
        // 바로 갱신된 값을 db 저장
        repository.saveAndFlush(stock);
        return stock.getQuantity();

    }
}
