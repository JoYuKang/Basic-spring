package basic.foundation.service;

import basic.foundation.domain.Member;
import basic.foundation.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     * @param member
     * @return Long(member id)
     */
    public Long signup(Member member){
        // 같은 이름을 가진 회원 = 중복 회원
        // Null 가능성이 있으면 Optional 사용하는 것을 권장 get()사용해서 꺼내도 같은 기능을 수행하지만
        // get()으로 객체를 직접 꺼내는 것을 권장하지 않는다. orElseGet()메소드를 더 권장
        // Optional<Member> result = memberRepository.findByName(member.getName());
        // result.ifPresent(member1 -> {throw new IllegalStateException("이미 존재하는 회원입니다.");});

        // 위 코드를 좀 더 간단하게 표현
        validateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }


    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 특정 회원 조회
     * @return
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
