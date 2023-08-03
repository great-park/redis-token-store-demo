package great.park.redis.controller;

import great.park.redis.dto.RedisKeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisTemplate<String, String> redisTemplate;

    @PostMapping("/redis")
    public ResponseEntity<?> addRediskey(
            @RequestBody RedisKeyValue redisKeyValue
    ) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();

        vop.set(redisKeyValue.getKey(), redisKeyValue.getValue());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/redis/{key}")
    public ResponseEntity<?> getRedisValue(
            @PathVariable String key
    ) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        String value = vop.get(key);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}
