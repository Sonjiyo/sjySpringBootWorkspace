package kr.boot.basic.service;

import jakarta.transaction.Transactional;
import kr.boot.basic.domain.Member;
import kr.boot.basic.repository.SpringJpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    //private final MemberRepository memberRepository;
    @Autowired
    private final SpringJpaMemberRepository memberRepository;

    //public MemberService(MemberRepository repository){
    //    this.memberRepository = repository;
    //}

    //회원가입
    public void join(Member member){
        if(!validateBuplicateMember(member)){
            memberRepository.save(member);
        }else{
            System.out.println("이미 존재하는 회원입니다.");
        }

    }

    //이름 중복검사
    private boolean validateBuplicateMember(Member member){
//        return memberRepository.findById(member.getId())
//                .ifPresent(m -> {throw new IllegalArgumentException("이미 존재하는 회원입니다");});
        return memberRepository.findByName(member.getName()).isPresent();
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 한 명 조회
    public Optional<Member> findOneMember(Long id){
        return memberRepository.findById(id);
    }
}
