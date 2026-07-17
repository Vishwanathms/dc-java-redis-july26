package com.example.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisitController {

    private static final String VISIT_COUNTER_KEY = "visit-count";

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public VisitController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/")
    public String index(Model model) {
        Long count = redisTemplate.opsForValue().increment(VISIT_COUNTER_KEY);
        model.addAttribute("count", count);
        return "index";
    }
}
