package com.project.chatboard.service;

import com.project.chatboard.dto.ChannelDetails;
import com.project.chatboard.dto.ChannelDto;
import com.project.chatboard.model.Channel;
import com.project.chatboard.repository.ChannelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ChannelService {
  private final ChannelRepository channelRepository;
  private final AuthService authService;
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

    public Long findAdminIdForPost(Long channel_id){
        List<Channel> allChannels=channelRepository.findAll();
        for(int i=0;i<allChannels.size();i++)
        {
            if(allChannels.get(i).getChannel_id()==channel_id)
                return allChannels.get(i).getAdmin_id();
        }
        return null;
    }

    public ChannelDetails channelDetails(Long channel_id){
        Optional<Channel> channel =channelRepository.findById(channel_id);
        ChannelDetails details=new ChannelDetails();
        details.setChannel_description(channel.get().getDescripition());
        details.setChannel_name(channel.get().getName());
        details.setAdmin(authService.findUserNameById(channel.get().getAdmin_id()));
        return details;
    }

    public List<Channel> findAllChannelsByUser(Long user_id)
    {
        List<Channel> allChannels=channelRepository.findAll();
        List<Channel> userChannels=new ArrayList<>();
        for(int i=0;i<allChannels.size();i++){
            if(allChannels.get(i).getAdmin_id().equals(user_id)){
                userChannels.add(allChannels.get(i));
            }
        }
        return userChannels;
    }

}
