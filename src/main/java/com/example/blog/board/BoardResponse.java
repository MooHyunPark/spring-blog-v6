package com.example.blog.board;


import com.example.blog._core.util.MyDate;
import com.example.blog.user.User;
import com.example.blog.user.UserResponse;
import lombok.Data;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private String createdAt;

        private Integer userId;
        private String username;

        private boolean isOwner = false;

        public DetailDTO(Board board, UserResponse.loginDTO user) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = MyDate.formatToStr(board.getCreatedAt());

            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
            if (user != null && board.getUser().getId() == user.getId()) {
                this.isOwner = true;
            }
        }
    }

    @Data
    public static class UpdateFormDTO {
        private int id;
        private String title;
        private String content;
        private String createdAt;

        public UpdateFormDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = MyDate.formatToStr(board.getCreatedAt());
        }
    }



    @Data
    public static class DTO {
        private int id;
        private String title;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }
}
