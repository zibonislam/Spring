package com.spring.manpower.controller;

import com.spring.manpower.repository.PostRepository;
import com.spring.manpower.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;
    @GetMapping("/posts/{id}")
    PostEntity one(@PathVariable Long id) {
        Optional<PostEntity> postEntity =  postRepository.findById(id);
        return postEntity.get();
    }

    @PutMapping("/posts/{id}")
    PostEntity replaceEmployee(@RequestBody PostEntity postEntity, @PathVariable Long id) {

        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(postEntity.getTitle());
                    post.setBody(postEntity.getBody());
                    return postRepository.save(post);
                })
                .orElseGet(() -> {
                    postEntity.setId(id);
                    return postRepository.save(postEntity);
                });
    }

    @DeleteMapping("/posts/{id}")
    void deleteEmployee(@PathVariable Long id) {
        postRepository.deleteById(id);
    }
}
