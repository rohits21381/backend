package com.skyrics.app.payloads;

import lombok.Data;

@Data
public class CommentDto {

	private Integer commentId;
	private String content;
	private ProductDto product;
}
