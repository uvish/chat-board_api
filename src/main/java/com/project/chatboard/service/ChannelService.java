package com.project.chatboard.service;

import com.project.chatboard.dto.ChannelDto;
import com.project.chatboard.model.Channel;
import com.project.chatboard.repository.ChannelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ChannelService {
  private final ChannelRepository channelRepository;
    public Channel save (ChannelDto channelRequest){
        Channel channel=new Channel();
        channel.setDescripition(channelRequest.getDescription());
        channel.setAdmin_id(channelRequest.getAdmin_id());
        channel.setName(channelRequest.getName());
         return channelRepository.save(channel);
    }
    public boolean CheckExisting(String name){
        int exists=0;
        List<Channel> channels=channelRepository.findAll();
        for(int i=0;i<channels.size();i++){
            if(channels.get(i).getName().equalsIgnoreCase(name))exists=1;
        }
        if(exists==1)return true;
        else return false;
    }

    public List<Channel> getAll(){
        return channelRepository.findAll();
    }

}
