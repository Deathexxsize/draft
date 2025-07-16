package com.example.demo.mapper;

import com.example.demo.dto.PostBriefDTO;
import com.example.demo.dto.UserWithPostsDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.entity.User;

import java.util.List;

public class UserMapper {
    public static UserResponseDTO mapToResponseDTO(User user) {
        UserResponseDTO userDTO = new UserResponseDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return  userDTO;
    }

    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO userDTO = new UserResponseDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }

    public static List<UserResponseDTO> toDTOList(List<User> users) {
        return users.stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public static UserWithPostsDTO toUserWithPostDTO(User user) {
        UserWithPostsDTO userWithPostsDTO = new UserWithPostsDTO();
        userWithPostsDTO.setUserId(user.getId());
        userWithPostsDTO.setUsername(user.getUsername());

        List<PostBriefDTO> postBriefDTOList = user.getPosts().stream()
                .map(post -> {
                    PostBriefDTO postBriefDTO = new PostBriefDTO();

                    postBriefDTO.setPostId(post.getId());
                    postBriefDTO.setTitle(post.getTitle());
                    postBriefDTO.setContent(post.getContent());

                    return postBriefDTO;
                })
                .toList();

        userWithPostsDTO.setPosts(postBriefDTOList);

        return userWithPostsDTO;

    }
}
