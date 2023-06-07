package com.app.blog.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.blog.entity.Comment;
import com.app.blog.entity.Post;
import com.app.blog.entity.User;
import com.app.blog.exceptions.ResouceNotFoundException;
import com.app.blog.payloads.CommentDto;
import com.app.blog.payloads.PostResponce;
import com.app.blog.repositries.CommentRepo;
import com.app.blog.repositries.PostRepo;
import com.app.blog.repositries.UserRepo;
import com.app.blog.services.commentService;


@Service
public class CommentImpl implements commentService {

	
	@Autowired
	private CommentRepo commentrepo;
	@Autowired
	private PostRepo postrepo;
	@Autowired
	private UserRepo userrepo;
	@Autowired
	private ModelMapper moddelmapper;
	@Override
	public CommentDto createComment(CommentDto commentdto, Integer post_id,Integer user_id) {
		// TODO Auto-generated method stub
		User user=this.userrepo.findById(user_id).orElseThrow(()->new ResouceNotFoundException("User", "UserId", user_id));
		Post post=this.postrepo.findById(post_id).orElseThrow(()->new ResouceNotFoundException("Post","PostId", post_id));
		Comment comment=this.moddelmapper.map(commentdto, Comment.class);
		comment.setContent(commentdto.getContent());
		comment.setPost(post);
		comment.setUser(user);
		Comment saveComment=this.commentrepo.save(comment);
		
	
		return this.moddelmapper.map(saveComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
		Comment comment=this.commentrepo.findById(commentId).orElseThrow(()->new ResouceNotFoundException("Comment", "CommentId", commentId));
		this.commentrepo.delete(comment);
		
	}	

}
