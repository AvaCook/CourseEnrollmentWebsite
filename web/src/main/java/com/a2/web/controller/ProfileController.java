package com.a2.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.a2.web.util.JwtUtil;

import java.util.Map;
//not used, testing only
@RestController
@RequestMapping("/api")
public class ProfileController {

    @GetMapping("/profile")
    public ResponseEntity<?> profile(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = JwtUtil.validateTokenAndGetUsername(token);
        if (username == null) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        return ResponseEntity.ok(Map.of("username", username));
    }
}
