package com.tatara.reward.reward.api;

import com.tatara.reward.reward.service.RewardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reward")
public class RewardController {

    private final RewardService rewardService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> calculateRewardPoints(@PathVariable Long userId) {
        Integer points = rewardService.calculatePoints(userId);
        return ResponseEntity.ok(new RewardPointsResponse(points));
    }

    @GetMapping("/{userId}/{month}/{year}")
    public ResponseEntity<?> calculateRewardPoints(@PathVariable Long userId,
                                                   @PathVariable Integer month,
                                                   @PathVariable Integer year) {
        Integer points = rewardService.calculatePoints(userId, month, year);
        return ResponseEntity.ok(new RewardPointsResponse(points));
    }
}
