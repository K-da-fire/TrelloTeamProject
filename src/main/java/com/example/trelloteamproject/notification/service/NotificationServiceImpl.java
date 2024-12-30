package com.example.trelloteamproject.notification.service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Value("${slack.bot-token}")
    private String slackBotToken;

    @Value("${slack.channel.monitor}")
    private String defaultChannel;

    /**
     * Slack 알림 메시지를 기본 채널로 전송
     *
     * @param message 전송할 메시지 내용
     */
    @Override
    public void sendMessageToSlack(String message) {
        MethodsClient methods = Slack.getInstance().methods(slackBotToken);

        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(defaultChannel) // 기본 채널로 메시지 전송
                .text(message)
                .build();

        try {
            ChatPostMessageResponse response = methods.chatPostMessage(request);

            if (!response.isOk()) {
                throw new RuntimeException("슬랙 메시지 전송 실패: " + response.getError());
            }
        } catch (Exception e) {
            throw new RuntimeException("슬랙 메시지 전송 중 오류가 발생했습니다.", e);
        }
    }
}

