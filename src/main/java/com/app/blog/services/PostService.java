package com.app.blog.services;

import java.util.List;

import com.app.blog.entity.Post;
import com.app.blog.payloads.PostDto;
import com.app.blog.payloads.PostResponce;

public interface PostService {

	PostDto createPost(PostDto postdto,Integer userId,Integer categoryId);
	
	
	
	PostDto updatePost(PostDto postdto,Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponce getallPost (Integer pagenumber,Integer pagesize,String sort,String sortDir);
	
	
	PostDto getPostById(Integer PostId);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	
	List<PostDto>getPostByUser(Integer userId);
	
	List<PostDto>finBypost(String keyword);
	
}
