package basic.core;

import basic.core.member.*;

public class MemberApp {
    public static void main(String[] args) {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        MemberService memberService = new MemberServiceImpl(memberRepository);

        Member member = new Member(1L, "민기", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findById(1L);
        System.out.println("new member : " + member.toString());
        System.out.println("find member : " + findMember.toString());
    }
}
