package com.qin.service.simple;

import com.github.pagehelper.PageInfo;
import com.qin.model.simple.Comments;
import com.qin.model.simple.CommentsExample;

import java.util.List;

public interface CommentsService {

    public boolean addComments(Comments comments);

    public boolean editComments(Comments comments);

    public Comments findCommentsById(Integer commentsId);

    List<Comments> selectByExample(CommentsExample example);

    List<Comments> select();

    public List<Comments> findCommentsByKeywords(String keywords);

    public PageInfo<Comments> findCommentsByPage(Integer pageNum, String keywords);

    public PageInfo<Comments> findCommentsByPage1(Integer pageNum, String keywords);

    public PageInfo<Comments> findCommentsByPage2(Integer pageNum, String keywords);

}