package jpabook.jpashop;

import jpabook.jpashop.Member;
import jpabook.jpashop.MemberRepository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Test
    @Transactional
    @Rollback(value = false)

    public void testMember() {
        Member member = new Member();
        member.setUsername("memberA");
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());

        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername())
        ;
        Assertions.assertThat(findMember).isEqualTo(member); //JPA 엔티티 동일성 보
        ;
    }
}