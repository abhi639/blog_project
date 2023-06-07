package com.app.blog.payloads;

import java.util.Set;

public class CommentDto {

	
	
private Integer commentId;

private String content;

private UserDto user;

public UserDto getUser() {
	return user;
}

public void setUser(UserDto user) {
	this.user = user;
}

public CommentDto(Integer commentId, String content, UserDto user) {
	super();
	this.commentId = commentId;
	this.content = content;
	this.user = user;
}

public Integer getCommentId() {
	return commentId;
}

public void setCommentId(Integer commentId) {
	this.commentId = commentId;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

//public PostDto getPost() {
//	return post;
//}
//
//public void setPost(PostDto post) {
//	this.post = post;
//}



public CommentDto() {
	super();
	// TODO Auto-generated constructor stub
}

}
