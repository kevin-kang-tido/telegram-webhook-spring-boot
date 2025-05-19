package com.example.telegrambot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramMessage {

    private Long update_id;
    private Message message;

    @Data
    public static class Message {
        private Long message_id;
        private Chat chat;
        private String text;
        private User from;

        @Data
        public static class Chat {
            private Long id;
            private String type;
        }

        @Data
        public static class User {
            private Long id;
            private String first_name;
            private String username;
        }
    }
}

