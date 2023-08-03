package great.park.redis.domain;

import great.park.redis.dto.AccessTokenRequest;
import great.park.redis.dto.AccessTokenResponse;
import great.park.redis.dto.RefreshTokenRequest;
import great.park.redis.dto.RefreshTokenResponse;
import great.park.redis.repository.MemberRepository;
import great.park.redis.repository.RefreshTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private static final String SECRET_KEY = "abcdefgabcdefgabcdefgabcdefgabcdefgabcdefg";
    private static final int ACCESS_TOKEN_EXPIRES = 30000;

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final SecretKey secretKey;

    public RefreshTokenService(final MemberRepository memberRepository,
                               final RefreshTokenRepository refreshTokenRepository) {
        this.memberRepository = memberRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public RefreshTokenResponse generateRefreshToken(final RefreshTokenRequest request) {
        Long memberId = memberRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(IllegalAccessError::new).getMemberId();

        RedisToken refreshToken = new RedisToken(memberId, UUID.randomUUID().toString());
        refreshTokenRepository.save(refreshToken);

        return new RefreshTokenResponse(refreshToken);
    }

    public AccessTokenResponse generateAccessToken(final AccessTokenRequest request) {
        System.out.println("request.getRefreshToken() = " + request.getRefreshToken());
        RedisToken refreshToken = refreshTokenRepository.findByRefreshToken(request.getRefreshToken())
                .orElseThrow();

        Long memberId = refreshToken.getMemberId();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + ACCESS_TOKEN_EXPIRES);

        String accessToken = Jwts.builder()
                .signWith(secretKey)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .setSubject(String.valueOf(memberId))
                .compact();

        return new AccessTokenResponse(accessToken);
    }

    public Long extractMemberId(final String accessToken) {
        String memberId = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
        return Long.parseLong(memberId);

    }
}
