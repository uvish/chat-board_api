package com.project.chatboard.controller;

import com.project.chatboard.dto.ChannelDto;
import com.project.chatboard.model.Channel;
import com.project.chatboard.service.ChannelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/channel")
@AllArgsConstructor
@CrossOrigin()
public class ChannelController {
    ChannelService channelService;

    @PostMapping("/create")
    public Channel createChannel (@RequestBody ChannelDto channelRequest){
        if(!channelService.CheckExisting(channelRequest.getName()))
           return  channelService.save(channelRequest);
        return null;
    }

    @GetMapping("/all")
    public List<Channel> getAll(){
        return  channelService.getAll();
    }
}
