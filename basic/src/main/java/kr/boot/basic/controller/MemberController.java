package kr.boot.basic.controller;

import kr.boot.basic.domain.Member;
import kr.boot.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/members")
@Controller
public class MemberController {
    @Autowired
    MemberService memberService;
    @GetMapping("/new")
    public String creatingForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/members";
    }

    @GetMapping("")
    public String List(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
