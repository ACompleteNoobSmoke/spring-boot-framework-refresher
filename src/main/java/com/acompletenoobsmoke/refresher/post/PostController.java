package com.acompletenoobsmoke.refresher.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Integer id, @RequestBody Post post) {

        return ResponseEntity.ok(postService.updatePost(id, post));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePost(@PathVariable("id") Integer id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Post With ID: " + id + " has been deleted");
    }
}
