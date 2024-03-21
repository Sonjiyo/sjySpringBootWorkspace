package kr.ex.querydsl.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
//import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest //통합 테스트
@Transactional // 트렌지션을 열어야 db에 값 전달 가능 => 전달 후 rollback
class HelloTest {
    @Autowired
    EntityManager em;

    @Test
   // @Commit rollback 안하고 저장되게 하는법
    void firstTest(){
        Hello hello = new Hello();
        Hello hello1 = new Hello();
        Hello hello2 = new Hello();
        Hello hello3 = new Hello();
        em.persist(hello);
        em.persist(hello1);
        em.persist(hello2);
        em.persist(hello3);

        List<Hello> list =  em.createQuery("select h from Hello h").getResultList();
        list.forEach(h-> System.out.println("h = " + h));
    }

    @Test
    void firstQueryDSL(){
        Hello hello1 = new Hello();
        Hello hello2 = new Hello();
        Hello hello3 = new Hello();
        Hello hello4 = new Hello();
        em.persist(hello1);
        em.persist(hello2);
        em.persist(hello3);
        em.persist(hello4);
        System.out.println("===================");

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QHello h = new QHello("hello");

        List<Hello> result = queryFactory.select(h.hello).from(h.hello).fetch();
        //QHello 클래스 안에 미리 static final hello객체를 만들어놓음
        Hello findHello = queryFactory.selectFrom(QHello.hello).fetchFirst();

        result.forEach(h1-> System.out.println("h1 = " + h1));
        System.out.println("findHello = " + findHello);

        assertThat(findHello).isEqualTo(hello2);
    }
}