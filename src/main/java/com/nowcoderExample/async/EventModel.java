package com.nowcoderExample.async;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class EventModel {
    private  EventType type;
    private int actorId;
    private int entityType;
    private int entityId;
    private int entityOwnerId;

    private Map<String,String>exts=new HashMap<>();

    public String getExt(String key){
        return exts.get(key);
    }
    public Map<String, String> getExts() {
        return exts;
    }


    public EventModel(EventType type){
        this.type=type;
    }

    public EventModel(){

    }

    public EventType getType() {
        return type;
    }

    public EventModel setType(EventType type) {
        this.type = type;
        return this;
    }

    public int getActorId() {
        return actorId;
    }

    public EventModel setActorId(int actorId) {
        this.actorId = actorId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public EventModel setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public EventModel setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityOwnerId() {
        return entityOwnerId;
    }

    public EventModel setEntityOwnerId(int entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }

    public EventModel setExt(String name, String value) {
        exts.put(name, value);
        return this;
    }



}
