package great.park.redis.controller;

import great.park.redis.domain.MemberService;
import great.park.redis.dto.MemberRequest;
import great.park.redis.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Long> createMember(
            @RequestBody MemberRequest request
    ) {
        Long memberId = memberService.createMember(request);
        return ResponseEntity.ok(memberId);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> findMe(
            @PathVariable Long memberId
    ) {
        MemberResponse memberResponse = memberService.getMemberById(memberId);
        return ResponseEntity.ok(memberResponse);
    }
}
