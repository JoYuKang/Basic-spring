package basic.servlet.web.frontcontroller.v4.controller;

import basic.servlet.domain.member.Member;
import basic.servlet.domain.member.MemberRepository;
import basic.servlet.web.frontcontroller.ModelView;
import basic.servlet.web.frontcontroller.v3.ControllerV3;
import basic.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> memberList = memberRepository.findAll();

        model.put("members", memberList);

        return "members";

    }
}
