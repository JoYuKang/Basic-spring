package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {


    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    @Transactional
    public Long join(Member member) {

        // 중복 회원 가입 확인
        validateDuplicateMember(member);

        memberRepository.save(member);

        return member.getId();

    }

    public void validateDuplicateMember(Member member) {
        List<Member> members = memberRepository.findByName(member.getName());
        if (!members.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    // 전체 회원 목록
    public List<Member>  findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.find(memberId);
    }


}
