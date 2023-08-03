package great.park.redis.dto;

import great.park.redis.domain.RedisToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenResponse {
    private String memberId;
    private String refreshToken;

    public RefreshTokenResponse(RedisToken redisToken) {
        this.memberId = redisToken.getMemberId().toString();
        this.refreshToken = redisToken.getRefreshToken();
    }
}
