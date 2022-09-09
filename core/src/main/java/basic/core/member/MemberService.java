package basic.core.member;

public interface MemberService {
    void join(Member member);

    Member findById(Long memberId);
}
