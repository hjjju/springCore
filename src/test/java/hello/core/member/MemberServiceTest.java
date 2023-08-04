package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl();


    MemberService memberService;


    @BeforeEach //테스트를 실해하기전에 무조건 실행, 테스트가 2개있으면 2번돔
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();

    }



    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then :검증
        //org.assertj
        Assertions.assertThat(member).isEqualTo(findMember);



    }
}
