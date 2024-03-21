package kr.study.jpa1.controller;

import kr.study.jpa1.domain.Member;
import kr.study.jpa1.domain.StudyRecode;
import kr.study.jpa1.form.StudyForm;
import kr.study.jpa1.service.MemberService;
import kr.study.jpa1.service.StudyRecodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {

    private final MemberService memberService;
    private final StudyRecodeService studyRecodeService;

    @GetMapping
    public String addForm(Model model){
        List<Member> members = memberService.getList();
        if(members == null) return "redirect:/member";
        model.addAttribute("members",members);

        model.addAttribute("curdate",LocalDate.now());
        model.addAttribute("curtime", LocalTime.now());

        return "study/addForm";
    }
    @PostMapping
    public String addStudyRocord(StudyForm studyForm){
        log.trace("form={}",studyForm);
        Member member = memberService.findId(studyForm.getMemberId());
        if(member==null) {
            log.error("{} 로그인 아이디가 존재하지 않음",studyForm.getMemberId());
            return "redirect:/";
        }
        studyRecodeService.saveRecode(studyForm, member);
        return "redirect:/";
    }

    @GetMapping("/records")
    public String getAllList(Model model){
        List<Member> members = memberService.getList();
        if(members==null) return "redirect:/";
        List<StudyRecode> list = studyRecodeService.getAllRecords();
        if(list ==null) return "redirect:/";
        model.addAttribute("list", list);
        return "study/list";
    }

    @GetMapping("/{keyId}")
    public String updateForm(@PathVariable Long keyId, Model model){
        StudyRecode recode = studyRecodeService.findId(keyId);
        model.addAttribute("record", recode);
        model.addAttribute("curdate",LocalDate.now());
        model.addAttribute("curtime", LocalTime.now());
        return "study/updateForm";
    }

    @PostMapping("/update")
    public String updateRecord(StudyForm studyForm, Long id){
        Member member = studyRecodeService.findId(id).getMember();
        studyRecodeService.updateRecord(studyForm,member, id);
        return "redirect:/study/records";
    }

    @DeleteMapping("/{keyId}")
    public @ResponseBody String updateForm(@PathVariable Long keyId){
        studyRecodeService.deleteRecord(keyId);
        return "ok";
    }
}
