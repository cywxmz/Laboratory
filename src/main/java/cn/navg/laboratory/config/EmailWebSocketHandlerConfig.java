package cn.navg.laboratory.config;

import cn.navg.laboratory.controller.common.EmailWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Component
public class EmailWebSocketHandlerConfig implements WebSocketConfigurer {
    @Autowired
    private EmailWebSocketHandler emailWebSocketHandler;
    
    // 注册WebSocketHandler 作用：注册WebSocketHandler到指定的URL路径
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(emailWebSocketHandler, "/email").setAllowedOrigins("*");
    }
}
