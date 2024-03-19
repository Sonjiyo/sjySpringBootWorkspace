import entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JpaMain {

    public static List<Customer> initcustomer(){
        List<Customer> list = new ArrayList<>();
        list.add(new Customer("ID100","test1"));
        list.add(new Customer("ID101","test2"));
        list.add(new Customer("ID102","test3"));
        list.add(new Customer("ID103","test4"));
        list.add(new Customer("ID104","test5"));
        list.add(new Customer("ID105","test6"));
        return list;
    }

    public static void main(String[] args) {

        //session Factory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-ex");
        EntityManager em = emf.createEntityManager(); //session 객체
        EntityTransaction tx = em.getTransaction();

        tx.begin(); //start transciton
        try {
            //영속성 컨테이너에서 값을 저장하는 두가지 방법
            Customer findCustomer = em.find(Customer.class, "ID002");
            System.out.println("findCustomer = "+findCustomer);

            Customer c = new Customer("ID005","test");
            em.persist(c);

            em.flush(); //db와 영속성 컨테이너의 데이터를 동기화해준다.(저장X)
            // 쓰기 지연 저장소에 있는 쿼리를 즉시 날린다

            em.clear(); //영속성 컨테이너 초기화


            findCustomer.setName("이름다시수정"); //변경 감지 : 최초 영속성 컨테이너에 저장되있는 스냅샷 객체와 비교
                                            //쓰기 지연 update에 저장
            System.out.println("findCustomer = "+findCustomer);
            
            
            
//            Customer c1 = new Customer("ID004", "Lee3");
//            Customer c2 = new Customer("ID005", "Park3");
//            em.persist(c1); //영속성 컨텍스트에 객체를 저장
//            em.persist(c2);
//            Customer c1 = em.find(Customer.class, "ID001");
//            em.remove(c1); //sql 쓰기 지연에다가 쿼리문 저장
//            System.out.println("c1 = "+c1);
//
//            Customer findCustomer1 = em.find(Customer.class, "ID005"); //이미 영속성 객체 안에 있느면 select 안나간다
//            Customer findCustomer2 = em.find(Customer.class, "ID002"); //영속성 컨텍스트에 객체가 없을때만 select 쿼리문이 나간다
//            System.out.println("findCustomer1 = "+findCustomer1);
//            System.out.println("findCustomer2 = "+findCustomer2);

            tx.commit(); //commit 쓰기 지연 저장소에 있는 sql 쿼리문(insert, update, delete)한꺼번에 나간다
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }

    }
}
