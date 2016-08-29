package com.nowcoderExample.model;

import java.util.Date;

/**
 * Created by Administrator on 2016/8/14 0014.
 */
public class Message {
    private int id;
    private String content;
    private Date createDate;
    private int fromId;
    private int hasRead;
    private String conversationId;

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    private int toId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createDate;
    }

    public void setCreatedDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getHasRead() {
        return hasRead;
    }

    public void setHasRead(int hasRead) {
        this.hasRead = hasRead;
    }

//    public String getConversationId() {
//        return conversationId;
//    }

//    public void setConversationId(String conversationId) {
//        this.conversationId = conversationId;
//    }

    public String getConversationId() {
        if (fromId < toId) {
            return String.format("%d_%d", fromId, toId);
        }
        return String.format("%d_%d", toId, fromId);
    }


}
