package com.skyrics.app.rest_controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyrics.app.payloads.ApiRespons;
import com.skyrics.app.payloads.CommentDto;
import com.skyrics.app.services.CommentService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/product/{productId}/comment")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable("productId") Integer productId){
		CommentDto createComment = this.commentService.createComment(comment, productId);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}
	
	@PutMapping("/Update_comment/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,@PathVariable("commentId") Integer commentId) {
		CommentDto updateComment = commentService.updateComment(commentDto, commentId);
		return new ResponseEntity<CommentDto>(updateComment,HttpStatus.UPGRADE_REQUIRED);
	}
	
	@GetMapping("/all-comments")
	public ResponseEntity<List<CommentDto>> getAllComment(){
		List<CommentDto> allComment = commentService.getAllComment();
		return new ResponseEntity<List<CommentDto>>(allComment,HttpStatus.OK);
	}

	@GetMapping("/comment/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable Integer commentId){
		System.out.println("get comment c start......");
		CommentDto commentById = commentService.getCommentById(commentId);
		return new ResponseEntity<CommentDto>(commentById,HttpStatus.ACCEPTED);

	}

	@GetMapping("/comment/postId/{postId}")
	public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable Integer postId){
		List<CommentDto> allCommentByPostId = commentService.getAllCommentByPostId(postId);
		return  new ResponseEntity<List <CommentDto>>(allCommentByPostId,HttpStatus.ACCEPTED);

	}
	
	@DeleteMapping("/comment/delete/{commentId}")
	public ResponseEntity<ApiRespons> deletComment(@PathVariable("commentId") Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiRespons>(new ApiRespons("comment deleted successfuly!",true) ,HttpStatus.OK);
	}
}
