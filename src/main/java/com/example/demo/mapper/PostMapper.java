package com.example.demo.mapper;

import com.example.demo.dto.PostResponseDTO;
import com.example.demo.entity.Post;

public class PostMapper {

    public static Post dtoToPost(PostResponseDTO postDto) {
        Post post = new Post();

        post.setTitle(post.getTitle());
        post.setContent(post.getContent());

        return post;
    }

    public static PostResponseDTO postToDTO(Post post) {
        PostResponseDTO responseDTO = new PostResponseDTO();

        responseDTO.setId(post.getId());
        responseDTO.setTitle(post.getTitle());
        responseDTO.setContent(post.getContent());

        return responseDTO;
    }
}
