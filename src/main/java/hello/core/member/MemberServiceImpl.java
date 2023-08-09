package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //얘가 가입을 하고 회원을 찾으려면 뭐가 필요하지?

    //ctrl + shift Enter하면;까지 자동완성
//    private final MemberRepository memberRepository = new MemoryMemberRepository(); //인터페이스가 필요(구현객체도 필요)
    private final MemberRepository memberRepository;  //MemberRepository 라는 인터페이스만 존재 <- 추상화에만 의존

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
