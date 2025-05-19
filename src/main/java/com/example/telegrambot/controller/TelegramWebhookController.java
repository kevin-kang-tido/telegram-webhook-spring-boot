
package com.example.telegrambot.controller;

import com.example.telegrambot.dto.TelegramMessage;
import com.example.telegrambot.service.MessageProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telegram")
@RequiredArgsConstructor
public class TelegramWebhookController {

    private final MessageProcessorService processorService;

@PostMapping("/webhook")
public ResponseEntity<String> receiveUpdate(@RequestBody TelegramMessage telegramMessage) {

    System.out.println("Received update: " + telegramMessage);

    try {

        processorService.processIncomingMessage(telegramMessage);

        return ResponseEntity.ok("Saved");

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("Failed to parse and save message");
    }}
}
