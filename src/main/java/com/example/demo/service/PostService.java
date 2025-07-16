package com.example.demo.service;

import com.example.demo.dao.PostDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.PostCreateDTO;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final UserDao userDao;
    private final PostDao postDao;

    public PostService(UserDao userDao, PostDao postDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }

    public Post createPost(int userId, PostCreateDTO postDTO) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with " + userId + " not found"));

        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setUser(user);

        return postDao.save(post);
    }
}
