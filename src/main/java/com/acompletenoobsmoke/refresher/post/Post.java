package com.acompletenoobsmoke.refresher.post;

public record Post(
        Integer id,
        Integer userId,
        String title,
        String body
) {
}
