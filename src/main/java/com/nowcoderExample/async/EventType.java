package com.nowcoderExample.async;

/**
 * Created by Administrator on 2016/8/22 0022.
 */

//.定义发生的事件
public enum EventType {

    LIKE(0),
    COMMENT(1),
    LOGIN(2),
    MAIL(3);

    private int value;
    EventType(int value){
        this.value=value;
    }
    public int getValue(){
        return value;
    }

}
