import entity.Customer;
import entity.Major;
import entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JpaMain {

    public static void init(EntityManager em){
        Student stu1 = new Student("김씨","1학년");
        Student stu2 = new Student("이씨","2학년");
        Student stu3 = new Student("박씨","3학년");
        Major m1 = new Major("컴싸","소프트엔지니어링");
        em.persist(m1);

        stu1.setMajor(m1);
        stu2.setMajor(m1);
        stu3.setMajor(m1);

        em.persist(stu1);
        em.persist(stu2);
        em.persist(stu3);
    }
    public static void main(String[] args) {
        //session Factory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-ex");
        EntityManager em = emf.createEntityManager(); //session 객체
        EntityTransaction tx = em.getTransaction();

        tx.begin(); //start transciton
        try {
            //init(em);
            Student findStudent = em.find(Student.class, 1L);
            System.out.println("findStudent = "+findStudent);

//            Major findMajor = em.find(Major.class, findStudent.getMajor());
//            System.out.println("findMajor = "+findMajor);
            tx.commit(); //commit 쓰기 지연 저장소에 있는 sql 쿼리문(insert, update, delete)한꺼번에 나간다
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }

    }
}
