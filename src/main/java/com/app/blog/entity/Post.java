package com.app.blog.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="post")
public class Post {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postiD;
     @Column(length = 100,nullable = false)
	private String title;
	
	@Column(length = 1000000)
	private String content;
	
	private String imageName;
	
	private Date addDate;
    @ManyToOne
   private Category category;
   @ManyToOne
    private User users;
   @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
   private List<Comment>comments=new ArrayList<>();

public Post(Integer postiD, String title, String content, String imageName, Date addDate, Category category, User users,
		List<Comment> comments) {
	super();
	this.postiD = postiD;
	this.title = title;
	this.content = content;
	this.imageName = imageName;
	this.addDate = addDate;
	this.category = category;
	this.users = users;
	this.comments = comments;
}
public Post() {
	super();
	// TODO Auto-generated constructor stub
}
public Integer getPostiD() {
	return postiD;
}
public void setPostiD(Integer postiD) {
	this.postiD = postiD;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getImageName() {
	return imageName;
}
public void setImageName(String imageName) {
	this.imageName = imageName;
}
public Date getAddDate() {
	return addDate;
}
public void setAddDate(Date addDate) {
	this.addDate = addDate;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
public User getUsers() {
	return users;
}
public void setUsers(User users) {
	this.users = users;
}
public List<Comment> getComments() {
	return comments;
}
public void setComments(List<Comment> comments) {
	this.comments = comments;
}
	
	



} 