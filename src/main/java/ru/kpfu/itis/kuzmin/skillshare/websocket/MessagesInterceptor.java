package ru.kpfu.itis.kuzmin.skillshare.websocket;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.kuzmin.skillshare.security.exception.AuthenticationHeaderException;
import ru.kpfu.itis.kuzmin.skillshare.security.util.SecurityUtil;

@Component
public class MessagesInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (accessor != null) {
            Authentication auth = (Authentication) accessor.getSessionAttributes().get("auth");
            if (auth != null) {
                accessor.setHeader("auth", auth);
            } else {
                throw new AuthenticationHeaderException("Authentication not found in session attributes");
            }
        }

        return message;
    }
}
