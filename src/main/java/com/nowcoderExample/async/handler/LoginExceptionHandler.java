package com.nowcoderExample.async.handler;

import com.nowcoderExample.async.EventHandler;
import com.nowcoderExample.async.EventModel;
import com.nowcoderExample.async.EventType;
import com.nowcoderExample.controller.IndexController;
import com.nowcoderExample.model.Message;
import com.nowcoderExample.service.MessageService;
import com.nowcoderExample.util.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by nowcoder on 2016/7/14.
 */
//实例化
@Component
public class LoginExceptionHandler implements EventHandler{
    @Autowired
    MessageService messageService;

    @Autowired
    MailSender mailSender;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        message.setToId(model.getActorId());
        message.setContent("你上次的登陆IP异常");
        // SYSTEM ACCOUNT
        message.setFromId(3);
        message.setCreatedDate(new Date());
        //setConversationId(String.format("%d_%d", model.getActorId(), 3));
        messageService.addMessage(message);

        Map<String, Object> map = new HashMap();
        map.put("username", model.getExt("username"));
        mailSender.sendWithHTMLTemplate(model.getExt("to"), "登陆异常",
                "mails/welcome.html", map);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LOGIN);
    }
}
