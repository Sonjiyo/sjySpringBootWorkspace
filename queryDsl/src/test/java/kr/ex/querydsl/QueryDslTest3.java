package kr.ex.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.ex.querydsl.domain.Member;
import kr.ex.querydsl.domain.MemberDTO;
import kr.ex.querydsl.domain.QMember;
import kr.ex.querydsl.domain.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.ex.querydsl.domain.QMember.member;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class QueryDslTest3 {
    @Autowired
    EntityManager em;
    JPAQueryFactory query;
    @BeforeEach
    void voidInitData(){
        //쿼리DSL 객체
        query = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        //영속성 컨텍스트 초기화
        em.flush();
        em.clear();
        System.out.println("===========================");
    }
    @Test
    void dtoByJQPL(){
        List<MemberDTO> result = em.createQuery(
                        "select new kr.ex.querydsl.domain.MemberDTO(m.username, m.age) " +
                                "from Member m", MemberDTO.class)
                .getResultList();
    }

    @Test
    public void 동적쿼리_BooleanBuilder() throws Exception {
        String usernameParam = "member1";
        Integer ageParam = 10;
        List<Member> result = searchMember1(usernameParam, ageParam);
        
        result.forEach(r-> System.out.println("r = " + r));
        
        Assertions.assertThat(result.size()).isEqualTo(1);
    }
    private List<Member> searchMember1(String usernameCond, Integer ageCond) {
        BooleanBuilder builder = new BooleanBuilder(); //where절을 동적으로 만들어줌
        if (usernameCond != null) {
            builder.and(member.username.eq(usernameCond));
        }
        if (ageCond != null) {
            builder.and(member.age.eq(ageCond));
        }
        return query
                .selectFrom(member)
                .where(builder)
                .fetch();
    }
}
