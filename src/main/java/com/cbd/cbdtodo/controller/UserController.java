package com.cbd.cbdtodo.controller;

import com.cbd.cbdtodo.dto.user.UserLoginRequest;
import com.cbd.cbdtodo.dto.user.UserSignupRequest;
import com.cbd.cbdtodo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/user")
//    public void signup(@RequestBody UserSignupRequest request){
//        userService.signupUser(request);
//    }

    // 유저 회원 가입
    @PostMapping("/user")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequest request) {
        try {
            userService.signupUser(request);
            return new ResponseEntity<>("성공적으로 회원 가입 되었습니다.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("서버에서 에러가 발생해 회원가입에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 유저 아이디 중복 확인
    @GetMapping("/user/check")
    public ResponseEntity<String> userIdDuplicateCheck(@RequestParam String userId) {
        try {
           userService.userIdDuplicateCheck(userId);
            // HTTP 204 No Content는 응답 본문을 포함하지 않는다고 한다. 그래서 일단 OK로 했다.
           return new ResponseEntity<>("사용 가능한 아이디입니다.",HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("서버에서 에러가 발생해 아이디 중복 확인에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 유저 로그인
    @GetMapping("/user")
    public ResponseEntity<String> login(@ModelAttribute UserLoginRequest request) {
        try {
            userService.login(request);
            return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }


}
