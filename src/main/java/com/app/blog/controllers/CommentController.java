package com.app.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.payloads.ApiResponce;
import com.app.blog.payloads.CommentDto;
import com.app.blog.payloads.PostDto;
import com.app.blog.services.commentService;




@RestController
@RequestMapping("/api")
public class CommentController {

	
	
	@Autowired
private commentService service; 
	

	
	@PostMapping("/comment/post/{postid}/user/{userId}/createcomment")
	public ResponseEntity<CommentDto> makeComment(@RequestBody CommentDto commenmtdto,@PathVariable("postid")Integer PostId,@PathVariable("userId")Integer userId) {
		System.out.println(commenmtdto.getContent());
		
	CommentDto savecomment=this.service.createComment(commenmtdto, PostId,userId);
	
	return new ResponseEntity<CommentDto>(savecomment,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deletecomment/{commentId}")
	public ApiResponce deleteComment(@PathVariable("commentId")Integer comeId)
	{
		
		
		this.service.deleteComment(comeId);
		return new ApiResponce("Comment Deleted Succesfully !!", true);
	}
}
