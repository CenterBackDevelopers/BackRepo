package com.cbd.cbdtodo.service;

import com.cbd.cbdtodo.domain.User;
import com.cbd.cbdtodo.domain.UserRepository;
import com.cbd.cbdtodo.dto.user.UserLoginRequest;
import com.cbd.cbdtodo.dto.user.UserSignupRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this. userRepository = userRepository;
    }

    @Transactional
    public void signupUser(UserSignupRequest request) {

        // 1. 아이디 특수문자 포함여부 확인
        if(!request.getUserId().matches("^[a-zA-Z0-9]*$")) {
            throw new IllegalArgumentException("특수문자를 사용하지 않는 아이디를 입력해주세요.");
        }
        // 2. 아이디 자릿수 확인
        else if(request.getUserId().length() < 5 || 15 < request.getUserId().length() ) {
            throw new IllegalArgumentException("아이디는 5~15자리여야 합니다.");
        }
        // 3. 비밀번호 자릿수 확인
        else if(request.getUserPassword().length() < 5 || 15 < request.getUserPassword().length() ) {
            throw new IllegalArgumentException("비밀번호는 6~20자리여야 합니다.");
        }
        // 4. 비밀번호 확인과 일치여부 확인
        else if(!request.getUserPassword().equals(request.getUserPasswordConfirm())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 5. 이름 자릿수 확인
        else if(request.getUserName().length() < 2 || 10 < request.getUserName().length() ) {
            throw new IllegalArgumentException("이름은 1~10자리여야 합니다.");
        }
        userRepository.save(new User
                (request.getUserId(), request.getUserPassword(), request.getUserPasswordConfirm(), request.getUserName()));
    }

    @Transactional
    public void userIdDuplicateCheck(String userId) {
        if(userRepository.existsByUserId(userId)) {c
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
    }

    //
    public void login(UserLoginRequest request) {
        if(!userRepository.existsByUserIdAndUserPassword(request.getUserId(), request.getUserPassword())){
            throw new IllegalArgumentException("아이디 또는 비밀번호가 틀렸습니다");
        }
    }
}
