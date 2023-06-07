package com.app.blog.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.blog.payloads.ApiResponce;
import com.app.blog.payloads.PostDto;
import com.app.blog.payloads.PostResponce;
import com.app.blog.services.ImageUploadService;
import com.app.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {

	
	@Autowired
	private PostService postservice;
	@Autowired
	private ImageUploadService imageservice;
	@Value(value = "${project.image}")
	String path;
	@PostMapping("user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto>createPost(@RequestBody PostDto post,@PathVariable("userId") Integer userId,@PathVariable("categoryId") Integer categoryId ){
		
		PostDto postcreated=this.postservice.createPost(post, userId, categoryId);
		
		return new ResponseEntity<PostDto>(postcreated,HttpStatus.CREATED);
		
		
		
	}
	//Get all post
	@GetMapping("/getallPostd")
	public ResponseEntity<PostResponce> allpost(@RequestParam(value = "pagenumber",defaultValue = "0",required = false)Integer pagenumber,@RequestParam(value = "pagesize",defaultValue = "5",required = false)Integer pagesize,@RequestParam(value = "sortBy",defaultValue = "postiD",required = false)String sortBy,@RequestParam(value = "sortDir",defaultValue = "asec",required = false)String sortDir) {
		PostResponce posts=this.postservice.getallPost(pagenumber,pagesize,sortBy,sortDir);
		
		return new ResponseEntity<PostResponce>(posts,HttpStatus.OK);
		
	}
	//get Post byId
	@GetMapping("/getPostByid/{postId}")
	public PostDto postById(@PathVariable("postId") Integer postId) {

		return this.postservice.getPostById(postId);
		
	}
	//deletepost
	@DeleteMapping("deletePost/{postId}")
	public ApiResponce deletePost(@PathVariable("postId") Integer postId){
		
		this.postservice.deletePost(postId);
		return new ApiResponce("Post deleted Succesfully !!",true ); 
	}
	
	//Update post by id
	@PutMapping("post/{postId}/updatepost")
	public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postdto,@PathVariable("postId") Integer postId){
		
		PostDto postdtos=this.postservice.updatePost(postdto, postId);
		
		return new ResponseEntity<PostDto>(postdtos,HttpStatus.OK);
	}
	
	
	@GetMapping("/category/{categoryId}/allPosts")
	public ResponseEntity<List<PostDto>>getPostByCategory(@PathVariable("categoryId") Integer catId){
		List<PostDto>posts=this.postservice.getPostByCategory(catId);
		System.out.println("data from controller==="+posts);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/category/{userId}/allPostsbyuser")
	public ResponseEntity<List<PostDto>>getPostByUser(@PathVariable("userId") Integer userId){
		List<PostDto>posts=this.postservice.getPostByUser(userId);
		System.out.println("data from controller==="+posts);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	@GetMapping("/searchBy/{key}")
	public ResponseEntity<List<PostDto>> searchbykey(@PathVariable("key")String key){
		
		List<PostDto>data=this.postservice.finBypost(key);
		return new ResponseEntity<List<PostDto>>(data,HttpStatus.OK);
		
	}

	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto>uploadImage(@RequestParam("image")MultipartFile file,@PathVariable("postId")Integer postId) throws IOException{
		
		String fileNmae=this.imageservice.uploaImage(path, file);
		System.out.println(fileNmae);
		PostDto post=this.postservice.getPostById(postId);
		post.setImageName(fileNmae);
		PostDto updatedpost=this.postservice.updatePost(post, postId);
		return new ResponseEntity<PostDto>(updatedpost,HttpStatus.OK);
	}
	
	@GetMapping(value ="post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException {
		System.out.println("image name=="+imageName);
InputStream resource = this.imageservice.getResource(path, imageName);
System.out.println("Resource name"+resource);
response. setContentType(MediaType. IMAGE_JPEG_VALUE);
StreamUtils.copy(resource, response. getOutputStream());
	}

}
