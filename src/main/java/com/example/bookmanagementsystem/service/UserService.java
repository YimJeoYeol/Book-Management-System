package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.domain.User;
import com.example.bookmanagementsystem.dto.UserDto;
import com.example.bookmanagementsystem.exception.NotFoundException;
import com.example.bookmanagementsystem.exception.UnAuthenticationException;
import com.example.bookmanagementsystem.repository.UserRepository;
import com.example.bookmanagementsystem.security.JwtManager;
import com.example.bookmanagementsystem.support.exception.ErrorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    private JwtManager jwtManager;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, JwtManager jwtManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtManager = jwtManager;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto regist(UserDto userDto) {
        isExist(userDto);
        return userRepository.save(userDto.toUser(passwordEncoder)).toUserDto();
    }

    public User login(String userId, String password) throws UnAuthenticationException {
        User user = findByUserId(userId);
        user.matchPassword(password, passwordEncoder);
        return user;
    }

    public User loginUser() {
        return userRepository.findByUserId(jwtManager.decode()).orElseThrow(() -> new NotFoundException(ErrorManager.NOT_EXIST_ID));
    }

    public void isExist(UserDto userDto) throws UnAuthenticationException {
        if (userRepository.findByUserId(userDto.getUserId()).isPresent()) {
            throw new UnAuthenticationException(ErrorManager.EXIST_ID);
        }
    }

    public String createToken(User loginUser) {
        return jwtManager.create(loginUser);
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorManager.NOT_EXIST_ID));
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException(ErrorManager.NOT_EXIST_ID));
    }


}