package com.basic.stock.service;

import com.basic.stock.entity.Stock;
import com.basic.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//mysql
//별도의 비용이 없으며, 어느정도의 트래픽까지는 사용가능
//redis보다 성능이 떨어짐

//redis
//별도로 db가 만들어지며, 프로그램 설치, 라이브러리 설치 필요
//mysql보다 성능이 좋음

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StockService {
    private final StockRepository repository;

    //스톡 추가..
    //스톡 삭제..
    //propagation = Propagation.REQUIRES_NEW : 에러가 전파가 안되고 자식만 따로 트랜잭션을 열어서 실행
    @Transactional(propagation = Propagation.REQUIRES_NEW) //트렉잭션을 새로 열어서(새로운 em 매니저, 영속성 컨텍스트) 시도한다
    public void decreaseStock(Long id, Long quantity){

        // stock 조회
        Stock stock = repository.findById(id).orElseThrow();  // null 이면 에러 출력
        // 재고 감소
        stock.decreaseStock(quantity);
        // 바로 갱신된 값을 db 저장
        repository.saveAndFlush(stock);

    }

}
