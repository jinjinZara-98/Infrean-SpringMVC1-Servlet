package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        //모든 객체 가져와 배열에 대입
        List<Member> members = memberRepository.findAll();
        //이름이 members인걸 가져와 대입
        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members);

        return mv;
    }
}
