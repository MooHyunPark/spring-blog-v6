package com.example.blog.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class loginDTO {
        private Integer id;
        private String username;

        public loginDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
        }

    }
}
