package com.nowcoderExample.async.handler;

import com.nowcoderExample.async.EventHandler;
import com.nowcoderExample.async.EventModel;
import com.nowcoderExample.async.EventType;
import com.nowcoderExample.model.Message;
import com.nowcoderExample.model.User;
import com.nowcoderExample.service.LikeService;
import com.nowcoderExample.service.MessageService;
import com.nowcoderExample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
@Component
public class LikeHandler implements EventHandler{
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Override
    public void doHandle(EventModel model) {

        // SYSTEM ACCOUNT

        Message message = new Message();
        User user = userService.getUser(model.getActorId());
        //message.setToId(model.getEntityOwnerId());
        message.setToId(model.getActorId());
        message.setContent("用户" + user.getName() +
                " 赞了你的资讯,http://127.0.0.1:8080/news/"
                + String.valueOf(model.getEntityId()));
        // SYSTEM ACCOUNT
        message.setFromId(3);
        message.setCreatedDate(new Date());
        //省略？
        //message.setConversationId(String.format("%d_%d", model.getActorId(), 3));

        messageService.addMessage(message);

 //System.out.println("Liked");
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }


}
