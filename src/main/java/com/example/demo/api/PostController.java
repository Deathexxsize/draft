package com.example.demo.api;
import com.example.demo.dto.PostCreateDTO;
import com.example.demo.dto.PostResponseDTO;
import com.example.demo.entity.Post;
import com.example.demo.mapper.PostMapper;
import com.example.demo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{id}/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostResponseDTO createPost(@Valid @PathVariable int id, @RequestBody PostCreateDTO postDTO) {
        Post post = postService.createPost(id, postDTO);
        return PostMapper.postToDTO(post);
    }
}
