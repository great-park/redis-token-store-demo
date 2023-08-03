package great.park.redis.controller;

import great.park.redis.domain.RefreshTokenService;
import great.park.redis.dto.AccessTokenRequest;
import great.park.redis.dto.AccessTokenResponse;
import great.park.redis.dto.RefreshTokenRequest;
import great.park.redis.dto.RefreshTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final RefreshTokenService tokenService;

    public AuthController(final RefreshTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> generateRefreshToken(@RequestBody final RefreshTokenRequest request) {
        RefreshTokenResponse refreshTokenResponse = tokenService.generateRefreshToken(request);
        return ResponseEntity.ok(refreshTokenResponse);
    }

    @PostMapping("/access-token")
    public ResponseEntity<AccessTokenResponse> generateAccessToken(@RequestBody final AccessTokenRequest request) {
        AccessTokenResponse accessTokenResponse = tokenService.generateAccessToken(request);
        return ResponseEntity.ok(accessTokenResponse);
    }
}
