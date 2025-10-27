package com.a2.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.a2.web.DTO.StudentDTO;
import com.a2.web.DTO.UserDto;
import com.a2.web.repository.StudentRepository;
import com.a2.web.service.StudentService;
//import com.a2.web.dtos.UserDto;
import com.a2.web.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;



@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private StudentService studentService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user, HttpServletResponse response) {

        String username = user.get("username");
        String password = user.get("password");

        //the business logic
        StudentDTO student = studentService.authenticateStudent(username, password);
        
        if (student!= null) {
            String accessToken = JwtUtil.generateAccessToken(username);
            String refreshToken = JwtUtil.generateRefreshToken(username);
            
            // Storing refresh token in an HttpOnly cookie
            Cookie cookie = new Cookie("refreshToken", refreshToken);
            cookie.setHttpOnly(true);   // HttpOnly cookie
            cookie.setSecure(true);         // sent only via HTTPS (encrypted channels)
            cookie.setPath("/api");  // where the cookie can be sent to
  
            
            cookie.setMaxAge(60 * 60);           // 1 hour
            response.addCookie(cookie);          // add the cookie to the response
                 
            return ResponseEntity.ok(Map.of("token", accessToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request) {
        
        // Find the refreshToken cookie
        String refreshToken = null;
        //if (request.getCookies() == null) return ResponseEntity.status(401).body("No cookies");

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("refreshToken")) {
                refreshToken = cookie.getValue();
                break;
            }
        }

        if (refreshToken == null) return ResponseEntity.status(401).body("Missing refresh token");

        // Check whether refresh token in valid
        String username = JwtUtil.validateRefreshToken(refreshToken);
        if (username == null) return ResponseEntity.status(401).body("Invalid refresh token");

        String newAccessToken = JwtUtil.generateAccessToken(username);
        return ResponseEntity.ok(Map.of("token", newAccessToken));
    }

    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response, HttpServletRequest request) {
        //logs user out of api
        if (request.getCookies() == null) return ResponseEntity.status(401).body("No cookies");
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("refreshToken")) {
                JwtUtil.invalidateRefreshToken(cookie.getValue());
                cookie.setMaxAge(0);
                cookie.setPath("/api");
                response.addCookie(cookie);
                break;
            }
        }
        return ResponseEntity.ok("Logged out");
    }

    //validates access token
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authToken) {
        
        var token  = authToken.replace("Bearer ", "");

        if (JwtUtil.validateTokenAndGetUsername(token)==null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid token"));
        else
            return ResponseEntity.ok(Map.of("message", "Valid token"));
            
    }
    //not used
    @GetMapping("/me")
    public ResponseEntity<?> me(){
     
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = authentication.getPrincipal();
        return ResponseEntity.ok(new UserDto(user.toString()));
    }
    
}
