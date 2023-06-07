package com.app.blog.repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
