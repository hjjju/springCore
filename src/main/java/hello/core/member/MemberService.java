package hello.core.member;


//ctrl + bac.getBean("memberService", MemberService.class);
public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);

}
