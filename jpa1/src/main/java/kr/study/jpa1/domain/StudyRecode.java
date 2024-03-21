package kr.study.jpa1.domain;

import jakarta.persistence.*;
import kr.study.jpa1.form.StudyForm;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
public class StudyRecode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyId; // study_id

    private LocalDate studyDay; // 미래시간 선택x 현재, 과거 선택
    private LocalTime startTime; // 오후 6:10
    private int studyMins; // 40
    @Lob
    private String contents;

//    @Transient
//    private String endday;

    // fk 값을 가지는가? (member_id),  == 연관관계의 주인 == @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    private Member member; //fk => member_id

    //생성자 메서드
    public static StudyRecode createRecord(StudyForm form, Member member){
        StudyRecode recode = new StudyRecode();
        recode.member = member;
        recode.studyDay = form.getStudyDay();
        recode.startTime = form.getStartTime();
        recode.studyMins = form.getStudyMins();
        recode.contents = form.getContents();
        return recode;
    }

    public String getEndStudyDay(){
        String pattern = "yyyy/MM/dd HH:mm";
        LocalDateTime endStudyTime = LocalDateTime.of(this.studyDay, this.startTime);
        endStudyTime = endStudyTime.plusMinutes(this.studyMins);
        String date = endStudyTime.format(DateTimeFormatter.ofPattern(pattern));
        return date;
    }
}
