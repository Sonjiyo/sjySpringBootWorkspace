package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Locker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int lockNo;
    @OneToOne(mappedBy = "locker")
    private Student student;

    public Locker(int lockNo, Student student){
        this.lockNo = lockNo;
        this.student = student;
        student.setLocker(this);
    }
}
