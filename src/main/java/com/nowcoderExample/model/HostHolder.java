package com.nowcoderExample.model;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
@Component
public class HostHolder
{
    private static  ThreadLocal<User> users=new ThreadLocal<>();
    public User getUser(){
        return users.get();
    }

    public void setUsers(User user){
        users.set(user);
    }

    public void clear(){
        users.remove();
    }
}
