package com.acompletenoobsmoke.refresher.post;

import com.acompletenoobsmoke.refresher.exception.ResourceNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final RestClient restClient;

    public PostService() {
        this.restClient = RestClient.create("https://jsonplaceholder.typicode.com");
    }

    public List<Post> getAllPosts() {
        return restClient.get().uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {});
    }

    public Post getPostById(Integer id) {
        return restClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ResourceNotFoundException("Could not find post with id " + id);
                })
                .body(Post.class);
    }

    public Post createPost(Post post) {
        return restClient.post()
                .uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    public Post updatePost(Integer id, Post post) {
        return restClient.put()
                .uri("/posts/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    public void deletePost(Integer id) {
        restClient.delete()
                .uri("/posts/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
