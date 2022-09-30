package basic.servlet.web.frontcontroller.v3.controller;

import basic.servlet.domain.member.Member;
import basic.servlet.domain.member.MemberRepository;
import basic.servlet.web.frontcontroller.ModelView;
import basic.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> memberList = memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.getModel().put("members", memberList);

        return mv;

    }
}
