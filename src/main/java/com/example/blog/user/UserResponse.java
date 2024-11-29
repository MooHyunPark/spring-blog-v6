package com.example.blog.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class loginDTO {
        private String username;

        public loginDTO(User user) {
            this.username = user.getUsername();
        }
    }
}
