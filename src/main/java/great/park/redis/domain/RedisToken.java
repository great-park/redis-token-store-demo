package great.park.redis.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@Getter
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 3)
public class RedisToken {

    @Id
    private Long memberId;

    private String refreshToken;
}
