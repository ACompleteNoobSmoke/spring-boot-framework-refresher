package com.acompletenoobsmoke.refresher.post;

import com.acompletenoobsmoke.refresher.exception.ResourceNotFoundException;
import com.acompletenoobsmoke.refresher.jsonplaceholder.JSONPlaceHolderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final JSONPlaceHolderService jsonPlaceHolderService;

    public PostService(@Qualifier("web-client") JSONPlaceHolderService jsonPlaceHolderService) {
       this.jsonPlaceHolderService = jsonPlaceHolderService;
    }

    public List<Post> getAllPosts() {
        return jsonPlaceHolderService.getPosts();
    }

    public Post getPostById(Integer id) {
        return jsonPlaceHolderService.getPostById(id);
    }

    public Post createPost(Post post) {
        return jsonPlaceHolderService.createPost(post);
    }

    public Post updatePost(Integer id, Post post) {
        return jsonPlaceHolderService.updatePost(id, post);
    }

    public void deletePost(Integer id) {
        jsonPlaceHolderService.deletePost(id);
    }
}
