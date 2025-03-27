package com.skyrics.app.services;
import java.util.List;

import com.skyrics.app.payloads.CommentDto;

public interface CommentService {

	//create comment
	CommentDto createComment(CommentDto commentDto, Integer productId);

	//update comment
	CommentDto updateComment(CommentDto commentDto,Integer commentId);

	//get comment buy commentId
	CommentDto getCommentById(Integer commentId);

	//get all comment
	List<CommentDto> getAllComment();

	//get comment by post id
	List<CommentDto> getAllCommentByPostId(Integer postId);

	//delete comment
	void deleteComment(Integer commentId);

}
