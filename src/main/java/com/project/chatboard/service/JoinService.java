package com.project.chatboard.service;

import com.project.chatboard.dto.JoinRequetDTO;
import com.project.chatboard.dto.JoinResponse;
import com.project.chatboard.model.Channel;
import com.project.chatboard.model.JoinRequests;
import com.project.chatboard.repository.ChannelRepository;
import com.project.chatboard.repository.JoinRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class JoinService {
    JoinRepository joinRepository;
    ChannelService channelService;
    AuthService authService;
    ChannelRepository channelRepository;
    public boolean admin_init(Long user_id,Long channel_id){
        JoinRequests request=new JoinRequests();
        request.setStatus("Admin");
        request.setUser_id(user_id);
        request.setChannel_id(channel_id);
        joinRepository.save(request);
        return true;
    }
    public String getStatus(Long user_id,Long channel_id){
        List<JoinRequests> allRequests=joinRepository.findAll();
//        If admin
        if(channelService.getChannelAdmin(channel_id).equals(user_id))
            return "Admin";

        for (JoinRequests allRequest : allRequests) {
            if (allRequest.getChannel_id().equals(channel_id) && allRequest.getUser_id().equals(user_id))
                return allRequest.getStatus();
        }
      return "Join";
    }
    public ResponseEntity<String> createRequest(JoinRequetDTO joinRequest){
         if(!checkExistingRequest(joinRequest.getUser_id(),joinRequest.getChannel_id())) {
            JoinRequests request = new JoinRequests();
            request.setChannel_id(joinRequest.getChannel_id());
            request.setUser_id(joinRequest.getUser_id());
            request.setStatus("Requested");
            request.setAdmin_id(channelService.getChannelAdmin(joinRequest.getChannel_id()));
            joinRepository.save(request);
            return new ResponseEntity<>("RequestCreated", HttpStatus.OK);
        }
        else{
            JoinRequests already_exist=findExisting(joinRequest.getUser_id(),joinRequest.getChannel_id());
            joinRepository.deleteById(already_exist.getRequest_id());
            return new ResponseEntity<>("RequestCancelled", HttpStatus.OK);
        }
    }

    public boolean checkExistingRequest(Long user_id,Long channel_id){
        List<JoinRequests> allRequests=joinRepository.findAll();
        for (JoinRequests allRequest : allRequests) {
            if (allRequest.getUser_id().equals(user_id) && allRequest.getChannel_id().equals(channel_id))
                return true;
        }
        return false;
    }
    public JoinRequests findExisting(Long user_id,Long channel_id){
        List<JoinRequests> allRequests=joinRepository.findAll();
        for (JoinRequests allRequest : allRequests) {
            if (allRequest.getUser_id().equals(user_id) && allRequest.getChannel_id().equals(channel_id))
                return allRequest;
        }
        return null;
    }
    public ResponseEntity<String> approveRequest(Long request_id){
        Optional<JoinRequests> og=joinRepository.findById(request_id);
        JoinRequests approved=new JoinRequests();
        approved.setRequest_id(og.get().getRequest_id());
        approved.setUser_id(og.get().getUser_id());
        approved.setChannel_id(og.get().getChannel_id());
        approved.setStatus("Joined");
        approved.setAdmin_id(og.get().getAdmin_id());
        joinRepository.save(approved);
        return new ResponseEntity<>("RequestApproved", HttpStatus.OK);
    }
    public ResponseEntity<String> denyRequest(Long request_id){
        joinRepository.deleteById(request_id);
        return new ResponseEntity<>("RequestDenied", HttpStatus.OK);
    }
    public List<JoinResponse> getAllRequestsForAdmin(Long admin_id){
        List<JoinRequests> allRequests=joinRepository.findAll();
        List<JoinResponse> requestsforadmin=new ArrayList<>();
        for (JoinRequests allRequest : allRequests) {
            if (allRequest.getAdmin_id().equals(admin_id) && !allRequest.getStatus().equals("Joined")) {
                JoinResponse response = new JoinResponse();
                response.setAdmin_id(allRequest.getAdmin_id());
                response.setRequest_id(allRequest.getRequest_id());
                response.setStatus(allRequest.getStatus());
                response.setChannel_id(allRequest.getChannel_id());
                response.setUser_id(allRequest.getUser_id());
                response.setUser_name(authService.findUserNameById(allRequest.getUser_id()));
                response.setChannel_name(channelService.getChannelNameById(allRequest.getChannel_id()));
                requestsforadmin.add(response);
            }

        }
        return requestsforadmin;
    }
    public List<Channel> getAllChannelsJoinedByUser(Long user_id){
        List<JoinRequests> allRequests = joinRepository.findAll();
        List<Channel> user_channels=new ArrayList<>();
        for(JoinRequests request:allRequests){
            if( (request.getUser_id().equals(user_id) && request.getStatus().equalsIgnoreCase("Joined")) || request.getAdmin_id().equals(user_id) ){
                user_channels.add(channelService.getChannelById(request.getChannel_id()));
            }
        }
        List<Channel> allChannel=channelRepository.findAll();
        for(Channel ch:allChannel){
            if(ch.getAdmin_id().equals(user_id)){
                user_channels.add(ch);
            }
        }
        return removeDuplicates(user_channels);
//      return user_channels;
    }
    public List<Channel> removeDuplicates(List<Channel> list)
    {
        Set<Channel> set=new HashSet<>(list);
        List<Channel> newList = new ArrayList<>(set);
        return newList;
    }
}
