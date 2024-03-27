package com.basic.stock.repository;

import com.basic.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LockRepository extends JpaRepository<Stock, Long> {
    //lock을 가져오는 것
    @Query(value = "select get_lock(:key, 3000)")
    void getLock(String key);

    //lock 해제 => 점유시간이 끝났을 때, 서비스 사용완료했을 때
    @Query(value = "select release_lock(:key)")
    void releaseLock(String key);
}
