package com.basic.stock.facade;

//facade : 건물 외벽

import com.basic.stock.service.OptimisticLockStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// optimistic lock 따로 데이터베이스 락을 걸지 않기 때문에
// 재시도 할 수 있는 로직(:재고 감소)을 따로 작성해야 함
@Service
@RequiredArgsConstructor
public class OptimisticLockStockFacade {
    private final OptimisticLockStockService service;

    public void decreseStock(Long id, Long quantity) throws InterruptedException{
        while (true){
            try{
                //접속 성공
                service.decreaseStock(id, quantity);
                return;
            }catch (Exception e){
                System.out.println("접속 실패");
                Thread.sleep(50); //기다렸다가 다시 시도
            }
        }
    }
}
