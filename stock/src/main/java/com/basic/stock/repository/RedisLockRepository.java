package com.basic.stock.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisLockRepository {
    private final RedisTemplate<String, String> redisTemplate;  // 레디스 명령어를 변경해주는 템플릿 라이브러리 클래스

    public Boolean lock(Long key){         // setnx 1 lock 3000
        return redisTemplate.opsForValue()    // key 라는 값으로 lock 을 거는데 3초동안 점유해라 라는 redis 명령어를 라이브러리로 구현한것
                .setIfAbsent(getGereateKey(key), "lock" , Duration.ofMillis(3_000));
    }

    public Boolean unLock(Long key){ // del 1
        return redisTemplate.delete(getGereateKey(key));
    }


    private String getGereateKey(Long key) {
        return key.toString();
    }


}
