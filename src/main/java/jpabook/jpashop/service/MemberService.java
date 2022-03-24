package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //트랜잭션안에서 값이 변경되어서 꼭필요하다.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;




    /**회원가입*/
    @Transactional(readOnly = false)
    public  Long join(Member member){

        validateDuplicateMember(member);//중복회원 검증
       memberRepository.save(member);
       return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers =
                memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회

    public  List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public  Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }


    @Transactional
    public void update(Long id, String name) {
    Member member = memberRepository.findOne(id);
    member.setName(name);
    }
}
