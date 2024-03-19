package kr.boot.basic.repository;

import kr.boot.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringJpaMemberRepository extends JpaRepository<Member, Long>{
    public Member findByName(String name);
}
