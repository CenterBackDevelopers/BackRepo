package com.cbd.cbdtodo.service;

import com.cbd.cbdtodo.domain.User;
import com.cbd.cbdtodo.domain.UserRepository;
import com.cbd.cbdtodo.dto.user.UserChangeUserPasswordRequest;
import com.cbd.cbdtodo.dto.user.UserLoginRequest;
import com.cbd.cbdtodo.dto.user.UserSignupRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        // 240824(토) 로직 수정함
        else if(request.getUserName().length() < 2 || 10 < request.getUserName().length() ) {
            throw new IllegalArgumentException("이름은 2~10자리여야 합니다.");
        }
        userRepository.save(new User
                (request.getUserId(), request.getUserPassword(), request.getUserPasswordConfirm(), request.getUserName()));
    }


    @Transactional
    public void userIdDuplicateCheck(String userId) {
        // 이미 존재하는 아이디인지 검증
        if(userRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
    }


    public void login(UserLoginRequest request) {
        // userId와 userPassword 를 DB에 던져 존재하는지 검증
        if(!userRepository.existsByUserIdAndUserPassword(request.getUserId(), request.getUserPassword())){
            throw new IllegalArgumentException("아이디 또는 비밀번호가 틀렸습니다");
        }
    }

    // Transactional 어노테이션 적용으로 Dirty Check가 작동해 user 객체에 변경사항 적용된 후 자동 save됨.
    @Transactional
    public void changeUserPassword(UserChangeUserPasswordRequest request) {
        /*
        // 원래 처음 하려던 방식으로, DB에 update문을 직접 날리려고 했다.
        // 0. 요청한 userId에 해당하는 User 객체를 찾아서 옵셔널로 감싸서 받아온다. 아이디로 비밀번호를 찾는 과정.
        Optional<User> OptUserReturnedByFindMethod = userRepository.findUserPasswordByUserId(request.getUserId());
        // 1. 비밀번호가 존재하는지 확인하고 없으면 예외를 던진다.
        if (OptUserReturnedByFindMethod.isEmpty()) {
            throw new IllegalArgumentException("유저를 찾지 못했거나 유저의 비밀번호가 존재하지 않습니다.");
        }
        // 2. 바꾸고 싶은 비밀번호가 기존 비밀번호와 다른지 검증한다.
            // 2-1. 옵셔널을 벗긴다.
        User user = OptUserReturnedByFindMethod.get();
            // 2-2. User 객체의 getUserPassword 로 우리가 찾은 유저의 기존 비밀번호를 가져온다.
        String password = user.getUserPassword();
            // 3. 검증 로직을 통해 만약 기존 비밀번호와 같다면 예외를 던진다.
        if(request.getUserPasswordWantToChange().equals(password)) {
            throw new IllegalArgumentException(("새 비밀번호는 기존 비밀번호와 같아서는 안됩니다."));
        }
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했거나 유저의 비밀번호가 존재하지 않습니다."));

         */

        // 0. 요청한 userId와 userPassword 에 해당하는 User 객체를 찾아서 옵셔널로 감싸서 받아온다. 아이디로 비밀번호를 찾는 과정.
        Optional<User> OptUserReturnedByFindMethod =
                userRepository.findByUserIdAndUserPassword(request.getUserId(), request.getUserPassword());

        // 1. 먼저 객체가 존재하는지 확인하고 없으면 예외를 던진다.
        if (OptUserReturnedByFindMethod.isEmpty()) {
            throw new IllegalArgumentException(("ID 또는 패스워드가 잘못 입력되었습니다."));
        }
        // 2. 비밀번호 변경을 시도한다
            // 2-1. 옵셔널을 벗긴다.
        User user = OptUserReturnedByFindMethod.get();
            // 2-2. User 객체의 updateName 메소드로 변경을 시도한다.
            // 기존 비밀번호와 같다면 여기서 예외가 던져진다.
        user.updatePassword(request.getUserPasswordWantToChange());
        // @Transactional 의 Dirty Check로 인해 자동 save된다.
    }

    //
    @Transactional
    public void deleteUser(String userId) {
        if(!userRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다. 아이디를 확인해주세요.");
        }
        userRepository.deleteById(userId);
    }

}
