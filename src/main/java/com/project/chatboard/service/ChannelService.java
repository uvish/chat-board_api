package com.project.chatboard.service;

import com.project.chatboard.dto.ChannelDetails;
import com.project.chatboard.dto.ChannelDto;
import com.project.chatboard.dto.JoinRequetDTO;
import com.project.chatboard.model.Channel;
import com.project.chatboard.model.JoinRequests;
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
    public Long getChannelAdmin(Long channel_id){
        Optional<Channel> ch=channelRepository.findById(channel_id);
        return ch.get().getAdmin_id();
    }
    public boolean CheckExisting(String name){
        int exists=0;
        List<Channel> channels=channelRepository.findAll();
        for (Channel channel : channels) {
            if (channel.getName().equalsIgnoreCase(name)) exists = 1;
        }
        if(exists==1)return true;
        else return false;
    }

    public List<Channel> getAll(){
        return channelRepository.findAll();
    }

    public Long findAdminIdForPost(Long channel_id){
        List<Channel> allChannels=channelRepository.findAll();
        for (Channel allChannel : allChannels) {
            if (allChannel.getChannel_id().equals(channel_id))
                return allChannel.getAdmin_id();
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
        for (Channel allChannel : allChannels) {
            if (allChannel.getAdmin_id().equals(user_id)) {
                userChannels.add(allChannel);
            }
        }
        return userChannels;
    }
    public String getChannelNameById(Long channel_id){
        Optional<Channel> ch= channelRepository.findById(channel_id);
        return ch.get().getName();
    }
    public Channel getChannelById(Long channel_id){
        Optional<Channel> ch=channelRepository.findById(channel_id);
        Channel found=new Channel();
        found.setChannel_id(ch.get().getChannel_id());
        found.setDescripition(ch.get().getDescripition());
        found.setName(ch.get().getName());
        found.setAdmin_id(ch.get().getAdmin_id());
        return found;
    }

}
