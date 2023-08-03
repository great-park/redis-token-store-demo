package great.park.redis.repository;

import great.park.redis.domain.RedisToken;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RefreshTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RefreshTokenRepository(final RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(final RedisToken redisToken) {
        System.out.println("redisToken = " + redisToken.getRefreshToken() + "    " + redisToken.getMemberId());
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set(String.valueOf(redisToken.getMemberId()), redisToken.getRefreshToken());
    }

    public Optional<RedisToken> findByRefreshToken(final String refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//        Long memberId = valueOperations.get(refreshToken);
        Long memberId = 1L;

        if (memberId == null) {
            return Optional.empty();
        }

        return Optional.of(new RedisToken(memberId, refreshToken));
    }
}