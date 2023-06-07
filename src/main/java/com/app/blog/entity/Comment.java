package com.app.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {

	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer commnetID;

private String content;
@ManyToOne
private Post post;
@ManyToOne
private User user;



public Comment(Integer commnetID, String content, Post post, User user) {
	super();
	this.commnetID = commnetID;
	this.content = content;
	this.post = post;
	this.user = user;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public Comment() {
	super();
	// TODO Auto-generated constructor stub
}

public Integer getCommnetID() {
	return commnetID;
}

public void setCommnetID(Integer commnetID) {
	this.commnetID = commnetID;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public Post getPost() {
	return post;
}

public void setPost(Post post) {
	this.post = post;
}




}
