package kr.boot.basic.controller;

import kr.boot.basic.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/members")
@Controller
public class MemberController {

    @GetMapping("/new")
    public String creatingForm(){
        return "members/createMemberForm";
    }

    @GetMapping("")
    public String List(Model model){
        List<Member> members = null;
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
