package great.park.redis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisKeyValue {
    private String key;
    private String value;
}
