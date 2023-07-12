package com.alive_backend.controller;

import com.alive_backend.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;

@RestController
public class WebSocketController {
    private final Logger logger = LoggerFactory.getLogger(WebSocketController.class); //

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private WebSocketService webSocketService;

    @MessageMapping("/send")
    @SendTo("/sub/chat")
    public String sendMessage(String value) {
        logger.info("发送消息内容：{}", value);
        return value;
    }

    @MessageMapping("/sendToUser")
    public void sendToUser(Principal principal, String body) {
        String srcUser = principal.getName();
        String[] args = body.split(": ");
        String desUser = args[0];
        String message = String.format("【%s】给你发来消息：%s", webSocketService.getNameMap().get(srcUser), args[1]);
        // 发送到用户和监听地址
        simpMessagingTemplate.convertAndSendToUser(desUser, "/queue/customer", message);

    }

}