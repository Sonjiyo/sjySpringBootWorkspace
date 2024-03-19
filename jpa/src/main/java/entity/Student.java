package entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="student_tbl")
@ToString(exclude = "major") //항상 연관관계가 있는 필드는 toString에서 제외해야한다 : toString 무한루프
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stuentId;
    private String name;
    private String grade;
    @ManyToOne(fetch =  FetchType.LAZY) // 관계구성 FetchType.EAGER : 즉시로딩 : 연관되어있는 모든 테이블
                                        // FetchType.LAZY : 지연 로딩
    @JoinColumn(name="majorId") //테이블 컬럼의 fk명
    private Major major;
    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(unique = true) //이름 안주면 클래스이름_id로 생성됨
    private Locker locker;
    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }
}