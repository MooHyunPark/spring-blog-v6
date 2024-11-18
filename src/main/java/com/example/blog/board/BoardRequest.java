package com.example.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data // getter, setter, toString
    public static class SaveDTO {
        private String title;
        private String content;


//        @Override
//        public String toString() {
//            return "SaveDTO{" +
//                    "title='" + title + '\'' +
//                    ", content='" + content + '\'' +
//                    '}';
//        }
    }

    @Data // getter, setter, toString
    public static class UpdateDTO {
        private String title;
        private String content;
    }


}
