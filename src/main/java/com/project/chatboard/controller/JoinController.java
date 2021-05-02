package com.project.chatboard.controller;

import com.project.chatboard.dto.JoinRequetDTO;
import com.project.chatboard.dto.JoinResponse;
import com.project.chatboard.model.JoinRequests;
import com.project.chatboard.service.JoinService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/join")
@AllArgsConstructor
@CrossOrigin()
public class JoinController {
    JoinService joinService;

    @PostMapping("/join_request")
    public ResponseEntity<String> createJoinRequest(@RequestBody JoinRequetDTO request){
        return joinService.createRequest(request);
    }
    @GetMapping("/approve/{id}")
    public ResponseEntity<String> approveRequest(@PathVariable("id")Long request_id){
        return joinService.approveRequest(request_id);
    }
    @GetMapping("/deny/{id}")
    public ResponseEntity<String> denyRequest(@PathVariable("id")Long request_id){
        return joinService.denyRequest(request_id);
    }

    @PostMapping("/status")
    public  String getStatus(@RequestBody JoinRequetDTO request){
        return joinService.getStatus(request.getUser_id(), request.getChannel_id());
    }

    @GetMapping("/getAllRequests/{id}")
    public List<JoinResponse>  getAllRequests(@PathVariable("id")Long admin_id){
        return joinService.getAllRequestsForAdmin(admin_id);
    }



}
