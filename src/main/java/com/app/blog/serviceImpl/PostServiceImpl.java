package com.app.blog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.blog.entity.Category;
import com.app.blog.entity.Comment;
import com.app.blog.entity.Post;
import com.app.blog.entity.User;
import com.app.blog.exceptions.ResouceNotFoundException;
import com.app.blog.payloads.CommentDto;
import com.app.blog.payloads.PostDto;
import com.app.blog.payloads.PostResponce;
import com.app.blog.repositries.CategoryRepo;
import com.app.blog.repositries.PostRepo;
import com.app.blog.repositries.UserRepo;
import com.app.blog.services.PostService;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postrepo;
	@Autowired
	private ModelMapper mpodelmapper;
	@Autowired
	private UserRepo userrepo;
	@Autowired
	private CategoryRepo categoryrepo;
	public PostDto createPost(PostDto postdto,Integer userId,Integer categoryid) {
		// TODO Auto-generated method stub
		
		User user=this.userrepo.findById(userId).orElseThrow(()-> new ResouceNotFoundException("User","Id",userId));
	
		System.out.println("afcate="+user.getName());
		System.out.println("afcate="+user.toString());
		Category category=this.categoryrepo.findById(categoryid).orElseThrow(()-> new ResouceNotFoundException("Category","Id",categoryid));
		
		Post post=this.mpodelmapper.map(postdto, Post.class);
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImageName("default.png");
		post.setAddDate(new Date());
		
		post.setUsers(user);
		post.setCategory(category);
		Post newpost=this.postrepo.save(post);
		
		System.out.println("date"+newpost.getAddDate());
		System.out.println("afcate="+newpost.getUsers().getName());
		
		
		return this.mpodelmapper.map(newpost, PostDto.class); 
	}

	

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postrepo.findById(postId).orElseThrow(()->new ResouceNotFoundException("Post", "PostId", postId));
		
		this.postrepo.delete(post);

	}

	

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		
		Post post=this.postrepo.findById(postId).orElseThrow(()->new ResouceNotFoundException("Post", "PostId", postId));
		System.out.println("Comments=="+post.getComments());
		List<Comment>p=post.getComments();
		
		p.forEach((e)->System.out.println("Content==="+e.getContent()));
		PostDto po=this.mpodelmapper.map(post,PostDto.class);
		
		List<CommentDto>pa=po.getComments();
		System.out.println(po.getComments());
		po.setComments(pa);
		return po;
	}

	

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userrepo.findById(userId).orElseThrow(()->new ResouceNotFoundException("User","userId",userId));
		System.out.print(user.getEmail()+user.getId());
		
		
		List<Post>users=this.postrepo.findByUsers(user);
		System.out.println("Users=="+users);
		List<PostDto>postDtos=users.stream().map((post)->this.mpodelmapper.map(post,PostDto.class)).collect(Collectors.toList());
		System.out.println(postDtos);
		return postDtos;
	}

	@Override
	public PostDto updatePost(PostDto postdto, Integer postId) {
		// TODO Auto-generated method stub
		
		Post post=this.postrepo.findById(postId).orElseThrow(()->new ResouceNotFoundException("post", "PostId",postId));
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImageName(postdto.getImageName());
		
		Post updatedPost=this.postrepo.save(post);
		
		
		return this.mpodelmapper.map(updatedPost, PostDto.class);
	}



	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category=this.categoryrepo.findById(categoryId).orElseThrow(()->new ResouceNotFoundException("Category","categoryId", categoryId));
		System.out.println("categoryId"+category.getCategoryId()+"category name=="+category.getCategoryTitle());
		List<Post>posts=this.postrepo.findByCategory(category);
		System.out.println("User Data==="+posts);
	List<PostDto>postDto=	posts.stream().map((post)->this.mpodelmapper.map(post, PostDto.class)).collect(Collectors.toList());
	
	System.out.println("data from service"+postDto);
		return postDto;
	}



	@Override
	public PostResponce getallPost(Integer pagenumber,Integer pagesize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asec")) {
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
			
		}
		Pageable p=PageRequest.of(pagenumber, pagesize,sort);
		
	Page<Post> pagepost=this.postrepo.findAll(p);
	List<Post>posts=pagepost.getContent();
	//System.out.println("posts=="+posts.get(0).getComments().get(0).getContent());
		List<PostDto>postDtos=posts.stream().map((post)->this.mpodelmapper.map(post,PostDto.class)).collect(Collectors.toList());
		PostResponce resp=new PostResponce();
		resp.setPosts(postDtos);
		resp.setPagenumber(pagepost.getTotalPages());
		resp.setPagesize(pagepost.getSize());
		resp.setLastpage(pagepost.isLast());
		resp.setTotalelement(pagepost.getTotalElements());
		resp.setTotalPages(pagepost.getTotalPages());
		return resp;
	}



	@Override
	public List<PostDto> finBypost(String keyword) {
		// TODO Auto-generated method stub
		List<Post>posts=this.postrepo.findByTitleContaining(keyword);
		System.out.println(posts.get(0));
		List<PostDto>postdtos=posts.stream().map((post)->this.mpodelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postdtos;
	}



	
	
}
