package kr.study.jpa1.service;

import kr.study.jpa1.domain.Member;
import kr.study.jpa1.domain.StudyRecode;
import kr.study.jpa1.form.StudyForm;
import kr.study.jpa1.repository.StudyRecodeRepositroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyRecodeService {
    private final StudyRecodeRepositroy recodeRepositroy;

    @Transactional
    public void saveRecode(StudyForm form, Member member){
        StudyRecode studyRecode = StudyRecode.createRecord(form, member);
        vaildTimeCheck(studyRecode);
        recodeRepositroy.save(studyRecode);
    }
    private void vaildTimeCheck(StudyRecode studyRecode){
        //if(recodeRepositroy.findByStartTime()){}

    }

    public List<StudyRecode> getAllRecords(){
        return recodeRepositroy.selectAll();
    }

    public StudyRecode findId(Long id){return recodeRepositroy.findById(id).get();}

    @Transactional
    public void updateRecord(StudyForm form,Member member, Long keyId){
        StudyRecode studyRecode = StudyRecode.createRecord(form, member);
        studyRecode.setStudyId(keyId);
        recodeRepositroy.save(studyRecode);
    }
    @Transactional
    public void deleteRecord(Long id){recodeRepositroy.deleteById(id);}

    @Transactional
    public void deleteAllRecordByMember(Member member){
        recodeRepositroy.deleteByMember(member);
//        List<StudyRecode> list = recodeRepositroy.searchStudyRecodeByMemberId(member.getId());
//        if(list != null){
//            list.forEach(recode -> {
//                log.trace("delete recode={}" , recode);
//                recodeRepositroy.deleteByMember(recode.getMember());
//            });
//        }
    }

}
