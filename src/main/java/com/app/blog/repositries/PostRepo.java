package com.app.blog.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entity.Category;
import com.app.blog.entity.Post;
import com.app.blog.entity.User;

public interface PostRepo extends JpaRepository<Post,Integer> {

	
	
	List<Post>findByUsers(User users);
	List<Post>findByCategory(Category category);

	List<Post> findByContent(String content);
List<Post>findByTitleContaining(String name);}
