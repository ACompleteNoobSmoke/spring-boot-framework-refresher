package com.acompletenoobsmoke.refresher.jsonplaceholder;

import com.acompletenoobsmoke.refresher.post.Post;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

public interface JSONPlaceHolderService {

    @GetExchange("/posts")
    List<Post> getPosts();

    @GetExchange("/posts/{id}")
    Post getPostById(@PathVariable("id") Integer id);

    @PostExchange("/posts")
    Post createPost(@RequestBody Post post);

    @PutExchange("/posts/{id}")
    Post updatePost(@PathVariable("id") Integer id, @RequestBody Post post);

    @DeleteMapping("/posts/{id}")
    void deletePost(@PathVariable("id")  Integer id);
}
