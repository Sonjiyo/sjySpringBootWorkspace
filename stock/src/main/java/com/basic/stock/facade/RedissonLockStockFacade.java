package com.basic.stock.facade;

import com.basic.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedissonLockStockFacade {
    private final RedissonClient redissonClient;
    private final StockService service;

    public void decrease(Long id, Long quantity){
        RLock lock = redissonClient.getLock(id.toString());

        try{                    //몇초동안 락 획득을 시도할 것인가(15초), 몇초동안 점유할 것인가
            boolean available = lock.tryLock(15,1, TimeUnit.SECONDS);
            if(!available){
                System.out.println("lock 획득 실패");
                return;
            }
            service.decreaseStock(id, quantity);
        }catch (Exception e){
            System.out.println("재고 감소 실패");
        }finally {
            lock.unlock();
        }
    }

}
