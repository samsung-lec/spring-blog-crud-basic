package shop.mtcoding.springblogriver.controller;

import shop.mtcoding.springblogriver.user.User;

public record JoinRecord(
        String username,
        String password,
        String email,
        String imgBase64) {
    User toEntity(String encPassword, String imgUrl){
        return User.builder()
                .username(username)
                .password(encPassword)
                .email(email)
                .imgUrl(imgUrl)
                .build();
    }
}