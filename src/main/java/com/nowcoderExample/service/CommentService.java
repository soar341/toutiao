package com.nowcoderExample.service;

import com.nowcoderExample.dao.CommentDAO;
import com.nowcoderExample.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
@Service
public class CommentService {
    @Autowired
    private CommentDAO commentDAO;

    public List<Comment> getCommentByEntity(int entityId,int entityType){
        return commentDAO.selectByEntity(entityId,entityType);
    }
    public int addComment(Comment comment) {
        return commentDAO.addComment(comment);
    }

    public int getCommentCount(int entityId, int entityType) {
        return commentDAO.getCommentCount(entityId, entityType);
    }

    public void deleteComment(int entityId,int entityType){
        commentDAO.UpdateStatus( entityId,entityType,1);

    }
}
