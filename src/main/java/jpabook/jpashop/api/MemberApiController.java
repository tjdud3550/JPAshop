package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController //데이터자체를 Xml 파일로보냄
@RequiredArgsConstructor

public class MemberApiController {
    private  final MemberService memberService;



    @PostMapping("api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return  new CreateMemberResponse(id);
    }
    //굳이..클래스,,? 엔터티를 외부에 노출하는것 금지

    @PostMapping("api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    //컴파일할때 영향 덜주기..

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request){
        memberService.update(id,request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }
    @Data

    static class UpdateMemberRequest{
        private  String name;
    }

    @Data @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;

        private  String name;

    }
    @Data
    static class CreateMemberRequest{
        private String name;

    }
    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id){
            this.id=id;
        }

    }

}
