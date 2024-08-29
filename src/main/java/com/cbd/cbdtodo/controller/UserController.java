package com.cbd.cbdtodo.controller;

import com.cbd.cbdtodo.dto.user.UserChangeUserPasswordRequest;
import com.cbd.cbdtodo.dto.user.UserDeleteRequest;
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
        } catch (Exception e) {
            return new ResponseEntity<>("서버에서 에러가 발생해 아이디 중복 확인에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/user")
    public ResponseEntity<String> changeUserPassword(@RequestBody UserChangeUserPasswordRequest request) {
        try {
            userService.changeUserPassword(request);
            return new ResponseEntity<>("비밀번호 변경 완료", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("서버에서 에러가 발생해 로그인에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    회원 탈퇴 기능은 로그인된 사용자만 접근할 수 있어야 한다.
    따라서 추후에 세션 또는 토큰에서 현재 로그인된 사용자의 ID를 가져와서 사용할 수 있도록 변경할 예정이다.
     */
    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestBody UserDeleteRequest requesst) {
        try {
            userService.deleteUser(requesst.getUserId());
            return new ResponseEntity<>("회원 탈퇴 완료", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("서버에서 에러가 발생해 로그인에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
