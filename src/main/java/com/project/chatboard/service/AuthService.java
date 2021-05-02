package com.project.chatboard.service;

import com.project.chatboard.dto.LoginRequest;
import com.project.chatboard.dto.RegisterRequest;
import com.project.chatboard.model.User;
import com.project.chatboard.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    public void signup(RegisterRequest registerRequest)
    {
        User user=new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setCreated(Instant.now());
        user.setFirstname(registerRequest.getFirstname());
        user.setLastname(registerRequest.getLastname());
        userRepository.save(user);
    }
    public boolean findExisting(RegisterRequest registerRequest){
        List<User> users=userRepository.findAll();
        for(User user:users){
            if(user.getEmail().equals(registerRequest.getEmail())){
                return true;
            }
        }
        return false;
    }
    public boolean findExisting(LoginRequest loginRequest){
        List<User> users=userRepository.findAll();
        for(User user:users){
            if(user.getEmail().equals(loginRequest.getEmail()) && user.getPassword().equals(loginRequest.getPassword())){
                return true;
            }
        }
        return false;
    }
    public boolean findExistingByToken(String token){
        String username=token.split(":")[0];
        String user_id=token.split(":")[1];
        Optional<User> users=userRepository.findById(Long.valueOf(user_id));
        if(users.isPresent() && users.get().getUsername().equals(username))
            return true;
        return false;
    }
    public String findUsername(String email){
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if ( user.getEmail().equals(email) ) {
                return user.getUsername();
            }
        }
        return "NA";
    }
    public Long findId(String email){
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if ( user.getEmail().equals(email) ) {
                return user.getUserId();
            }
        }
        return Long.valueOf(0);
    }
    public String findUserNameById(Long id){
        return userRepository.findById(id).get().getUsername();
    }
}
