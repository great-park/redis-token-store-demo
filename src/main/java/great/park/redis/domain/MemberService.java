package great.park.redis.domain;

import great.park.redis.dto.MemberRequest;
import great.park.redis.dto.MemberResponse;
import great.park.redis.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long createMember(MemberRequest request) {
        Member member = new Member(request.getEmail(), request.getPassword());
        return memberRepository.save(member).getMemberId();
    }

    public MemberResponse getMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return new MemberResponse(member);
    }
}
