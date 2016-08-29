package com.nowcoderExample.controller;

//import com.nowcoderExample.async.EventModel;
//import com.nowcoderExample.async.EventProducer;
//import com.nowcoderExample.async.EventType;
import com.nowcoderExample.async.EventModel;
import com.nowcoderExample.async.EventProducer;
import com.nowcoderExample.async.EventType;
import com.nowcoderExample.model.EntityType;
import com.nowcoderExample.model.HostHolder;
import com.nowcoderExample.model.News;
import com.nowcoderExample.service.LikeService;
import com.nowcoderExample.service.NewsService;
import com.nowcoderExample.util.ToutiaoUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by nowcoder on 2016/7/13.
 */
@Controller
public class LikeController {
    @Autowired
    LikeService likeService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    NewsService newsService;

   @Autowired
   EventProducer eventProducer;

    @RequestMapping(path = {"/like"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String like(@Param("newId") int newsId) {
       int userId=hostHolder.getUser().getId();
        //long likeCount = likeService.like(hostHolder.getUser().getId(), EntityType.ENTITY_NEWS, newsId);
        long likeCount = likeService.like(userId, EntityType.ENTITY_NEWS, newsId);
        // 更新喜欢数
        News news = newsService.getById(newsId);
        newsService.updateLikeCount(newsId, (int) likeCount);
//        eventProducer.fireEvent(new EventModel(EventType.LIKE)
//                .setEntityOwnerId(news.getUserId())
//                .setActorId(hostHolder.getUser().getId()).setEntityId(newsId));
        eventProducer.fireEvent(new EventModel(EventType.LIKE)
                .setActorId(hostHolder.getUser().getId()).setEntityId(newsId)
                .setEntityType(EntityType.ENTITY_NEWS).setEntityOwnerId(news.getUserId()));
        return ToutiaoUtil.getJSONString(0, String.valueOf(likeCount));
    }

    @RequestMapping(path = {"/dislike"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String dislike(@Param("newId") int newsId) {
        int userId=hostHolder.getUser().getId();
        //long likeCount = likeService.disLike(hostHolder.getUser().getId(), EntityType.ENTITY_NEWS, newsId);
        long likeCount = likeService.disLike(userId, EntityType.ENTITY_NEWS, newsId);
        // 更新喜欢数
        newsService.updateLikeCount(newsId, (int) likeCount);
        return ToutiaoUtil.getJSONString(0, String.valueOf(likeCount));
    }
}
