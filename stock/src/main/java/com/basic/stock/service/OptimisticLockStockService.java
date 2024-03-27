package com.basic.stock.service;

import com.basic.stock.entity.Stock;
import com.basic.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 버전을 만들어서 db 버전과 서버에서 가져온 버전이 일치할 때만
// db를 수정, 삭제, 삽입 할 수 있음
// 데이터를 빈번하게 중복해서 건드리지 않을 때 => 실무에서는 더 많이 사용
@Service
@RequiredArgsConstructor
public class OptimisticLockStockService {
    private final StockRepository repository;

    @Transactional
    public Long decreaseStock(Long id, Long quantity){

        // stock 조회
        Stock stock = repository.findByIdWithOptimisticLock(id);
        // 재고 감소
        stock.decreaseStock(quantity);
        // 바로 갱신된 값을 db 저장
        repository.saveAndFlush(stock);
        return stock.getQuantity();

    }
}
