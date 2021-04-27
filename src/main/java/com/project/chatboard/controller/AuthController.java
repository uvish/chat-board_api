package com.project.chatboard.controller;

import com.project.chatboard.dto.LoginRequest;
import com.project.chatboard.dto.RegisterRequest;
import com.project.chatboard.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin()
public class AuthController {

    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup (@RequestBody RegisterRequest registerRequest){
      if(!authService.findExisting(registerRequest)){
          authService.signup(registerRequest);
          return new ResponseEntity<>("Registered", HttpStatus.OK);
      }
      else{
          return new ResponseEntity<>("Already Exist",HttpStatus.OK);
      }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody LoginRequest loginRequest){
        if (loginRequest == null) return new ResponseEntity<>("Invalid Id",HttpStatus.BAD_REQUEST);
        if (authService.findExisting(loginRequest))
        {
            String username=authService.findUsername(loginRequest.getEmail());
            long id=authService.findId(loginRequest.getEmail());
            return ResponseEntity.ok(username+":"+id);
        }
        else
        {
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }
    }


}
