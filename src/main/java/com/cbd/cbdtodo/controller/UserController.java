package com.cbd.cbdtodo.controller;

import com.cbd.cbdtodo.dto.user.UserSignupRequest;
import com.cbd.cbdtodo.exception.user.UserIdAlreadyExistsException;
import com.cbd.cbdtodo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/user")
    // 현재 회원가입 기능 구현 완료, 예외처리 개발 완료. last_visited_date는 구현이 어려워 잠시 미뤄둠
    public ResponseEntity<String> signup(@RequestBody UserSignupRequest request) {
        try {
            userService.signupUser(request);
            return new ResponseEntity<>("성공적으로 회원 가입 되었습니다.", HttpStatus.CREATED);
        } catch (UserIdAlreadyExistsException e) {
            return new ResponseEntity<>("이미 존재하는 아이디입니다.", HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("서버에서 에러가 발생해 회원가입에 실패했습니다.",HttpStatus.CONFLICT);
        }
    }
}
