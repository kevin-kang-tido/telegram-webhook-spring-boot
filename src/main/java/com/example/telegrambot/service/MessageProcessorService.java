
package com.example.telegrambot.service;

import com.example.telegrambot.dto.TelegramMessage;
import com.example.telegrambot.model.TelegramMessageEntity;
import com.example.telegrambot.repository.TelegramMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageProcessorService {

    private final TelegramMessageRepository telegramMessageRepository;

    public void processIncomingMessage(TelegramMessage telegramMessage) {

        if (telegramMessage.getMessage() == null || telegramMessage.getMessage().getFrom() == null) {
            log.warn("Unsupported or malformed update: {}", telegramMessage);
            return;
        }

        var msg = telegramMessage.getMessage();

        var from = msg.getFrom();

        TelegramMessageEntity entity = TelegramMessageEntity.builder()
                .chatId(msg.getChat().getId())
                .userId(from.getId())
                .messageId(msg.getMessage_id())
                .username(from.getUsername())
                .messageText(msg.getText())
                .timestamp(LocalDateTime.now())
                .build();

        // save the insert data to the database
        telegramMessageRepository.save(entity);

        log.info("Saved message from @{}: {}", from.getUsername(), msg.getText());
    }

}

