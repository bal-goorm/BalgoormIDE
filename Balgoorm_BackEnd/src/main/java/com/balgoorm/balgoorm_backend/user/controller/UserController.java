package com.balgoorm.balgoorm_backend.user.controller;

import com.balgoorm.balgoorm_backend.user.auth.CustomUserDetails;
import com.balgoorm.balgoorm_backend.user.dto.request.UserLoginRequest;
import com.balgoorm.balgoorm_backend.user.dto.request.UserSignupRequest;
import com.balgoorm.balgoorm_backend.user.dto.request.UserUpdateRequest;
import com.balgoorm.balgoorm_backend.user.model.entity.User;
import com.balgoorm.balgoorm_backend.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequest request) {
        if (request.getUserId().isEmpty()) {
            return new ResponseEntity<>("User ID cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
        if (request.getPassword().isEmpty()) {
            return new ResponseEntity<>("Password cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
        if (request.getNickname().isEmpty()) {
            return new ResponseEntity<>("Nickname cannot be null or empty", HttpStatus.BAD_REQUEST);
        }

        userService.signup(request);
        log.info("회원가입 {}", request);
        return new ResponseEntity<>("Signup successful", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        if (userLoginRequest.getUserId().isEmpty()) {
            return new ResponseEntity<>("User ID cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
        if (userLoginRequest.getPassword().isEmpty()) {
            return new ResponseEntity<>("Password cannot be null or empty", HttpStatus.BAD_REQUEST);
        }

        User user = userService.login(userLoginRequest);

        if (user == null) {
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }

        log.info("로그인 {}", userLoginRequest);
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }


    //회원 정보 수정 (닉네임, 비밀번호)

    @PostMapping("/updateUser/{userid}")
    public ResponseEntity<String> updateUser(@PathVariable("userid") String userId, @RequestBody UserUpdateRequest request) {
        userService.updateUser(request, userId);
        return ResponseEntity.ok("User updated successfully");
    }

    //회원 탈퇴
    @DeleteMapping("/deleteUser/{userid}")
    public ResponseEntity<String> deleteUser(@PathVariable("userid") String userId, @RequestParam String password) {
        userService.deleteUser(userId, password);
        return ResponseEntity.ok("User deleted successfully");
    }
    @GetMapping("/current-user")
    public ResponseEntity<Map<String, String>> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        Map<String, String> response = new HashMap<>();
        response.put("username", userDetails.getUsername());
        response.put("userId", ((CustomUserDetails) userDetails).getUserId()); // CustomUserDetails에서 userId를 가져옵니다.
        return ResponseEntity.ok(response);
    }

}
