package com.app.blog.payloads;

import java.util.List;

public class PostResponce {
 
	
	private List<PostDto>posts;
	
	private Integer pagesize;
	
	private Integer pagenumber;
	private boolean lastpage;
	
	private  Long totalelement;
	private Integer totalPages;
	public Integer getTotalPages() {
		return totalPages;
	}

	public boolean getLastpage() {
		return lastpage;
	}

	public void setLastpage(boolean lastpage) {
		this.lastpage = lastpage;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}



	public List<PostDto> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDto> posts) {
		this.posts = posts;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getPagenumber() {
		return pagenumber;
	}

	public void setPagenumber(Integer pagenumber) {
		this.pagenumber = pagenumber;
	}

	public Long getTotalelement() {
		return totalelement;
	}

	public void setTotalelement(Long totalelement) {
		this.totalelement = totalelement;
	}



	
	

	public PostResponce(List<PostDto> posts, Integer pagesize, Integer pagenumber, boolean lastpage, Long totalelement,
			Integer totalPages) {
		super();
		this.posts = posts;
		this.pagesize = pagesize;
		this.pagenumber = pagenumber;
		this.lastpage = lastpage;
		this.totalelement = totalelement;
		this.totalPages = totalPages;
		
	}

	public PostResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
