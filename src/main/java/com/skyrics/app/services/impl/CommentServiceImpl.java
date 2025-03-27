package com.skyrics.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skyrics.app.dao.CommentRepository;
import com.skyrics.app.entities.Comment;
import com.skyrics.app.entities.Product;
import com.skyrics.app.exceptions.ResourceNotFoundException;
import com.skyrics.app.payloads.CommentDto;
import com.skyrics.app.payloads.ProductDto;
import com.skyrics.app.services.CommentService;
import com.skyrics.app.services.ProductService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer productId) {
		ProductDto productDto = productService.getProductById(productId);
		commentDto.setProduct(productDto);
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
	    Comment savedComment = this.commentRepository.save(comment);
		return this.modelMapper.map(savedComment,CommentDto.class);
	}
	
	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
		Comment comment = commentRepository.findBycommentId(commentId);
		comment.setContent(commentDto.getContent());
		Comment save = this.commentRepository.save(comment);
		CommentDto map = modelMapper.map(save,CommentDto.class);
		return map;
	}

	@Override
	public CommentDto getCommentById(Integer commentId) {
		Comment comment = commentRepository.findBycommentId(commentId);
		CommentDto comment1 = modelMapper.map(comment, CommentDto.class);
		return comment1;
	}

	@Override
	public List<CommentDto> getAllComment() {
		List<Comment> comments = commentRepository.findAll();
		List<CommentDto> commentDto = comments.stream().map((comment)-> this.modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
		return commentDto;
	}


	@Override
	public List<CommentDto> getAllCommentByPostId(Integer postId) {
		ProductDto productDto = productService.getProductById(postId);
		Product product = modelMapper.map(productDto, Product.class);
		List<Comment> comments = commentRepository.findByProduct(product);
		List<CommentDto> collect = comments.stream().map((comment)-> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = commentRepository.findBycommentId(commentId);
		if(comment != null) {
			this.commentRepository.delete(comment);
		}else {
			throw new ResourceNotFoundException("comment", "commentId", commentId);
		}
	}
}
