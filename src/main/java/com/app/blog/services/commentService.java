package com.app.blog.services;

import com.app.blog.payloads.CommentDto;

public interface commentService {

	
	CommentDto createComment(CommentDto comment, Integer post_id,Integer user_id);
	
	
	void deleteComment(Integer commentId);
}
