package co.edu.icesi.introspringboot2.websocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final CustomWebSocketHandler customWebSocketHandler;

    public WebSocketConfig(CustomWebSocketHandler handler) {
        this.customWebSocketHandler = handler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //ws://localhost:8080/ws/chat
        registry.addHandler(customWebSocketHandler, "/ws/chat")
                .setAllowedOrigins("http://localhost:5173");
    }
}
