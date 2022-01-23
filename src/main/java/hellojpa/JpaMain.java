package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 엔티티매니저팩토리는 하나만 생성해서 Application 전체 공유
        EntityManager em = emf.createEntityManager();
        // 엔티티매니저는 스레드간 공유X (사용하고 버린다, 공유시 에러발생)
        EntityTransaction tx = em.getTransaction();
        // JPA 모든 데이터변경은 트랜잭션 안에서 실행한다 **
        tx.begin();

        try{
//      insert
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//            em.persist(member);

//      select
//            Member findM = em.find(Member.class, 1L);
//            System.out.println("findM.id = " + findM.getId());
//            System.out.println("findM.Name = " + findM.getName());

//      update
            Member findM = em.find(Member.class, 1L);

            findM.setName("HelloJPA");
            // 커밋시점에 변경사항 있으면 자동저장되므로 persist 필요X

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
