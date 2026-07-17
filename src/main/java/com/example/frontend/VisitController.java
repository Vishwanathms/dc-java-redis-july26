package com.example.frontend;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisitController {

    private static final String VISIT_COUNTER_KEY = "visit-count";

    private final StringRedisTemplate redisTemplate;
    private final VisitLogRepository visitLogRepository;

    @Autowired
    public VisitController(StringRedisTemplate redisTemplate, VisitLogRepository visitLogRepository) {
        this.redisTemplate = redisTemplate;
        this.visitLogRepository = visitLogRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        Long redisCount = redisTemplate.opsForValue().increment(VISIT_COUNTER_KEY);
        visitLogRepository.save(new VisitLog(Instant.now()));
        long mysqlCount = visitLogRepository.count();

        model.addAttribute("redisCount", redisCount);
        model.addAttribute("mysqlCount", mysqlCount);
        return "index";
    }
}
