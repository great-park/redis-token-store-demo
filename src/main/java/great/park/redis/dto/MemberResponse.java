package great.park.redis.dto;

import great.park.redis.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
    private String email;
    private Long memberId;

    public MemberResponse(Member member) {
        this.email = member.getEmail();
        this.memberId = member.getMemberId();
    }
}
