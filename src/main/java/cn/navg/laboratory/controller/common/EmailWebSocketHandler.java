package cn.navg.laboratory.controller.common;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EmailWebSocketHandler implements WebSocketHandler {
    //定义一个WebSocketSession 作用：用于在WebSocketHandler中管理WebSocket会话
    private static final Set<WebSocketSession> sessions= ConcurrentHashMap.newKeySet();
    
    // 连接成功后调用
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //将已连接的用户添加到sessions集合中
        sessions.add(session);
    }
    
    // 收到消息后调用
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    
    }
    
    // 传输错误后调用
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    
    }
    
    // 连接关闭后调用
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    
    }
    //发送消息给所有已连接的用户 作用：将消息发送给所有已连接的用户
    public void sendMessageToAllUsers(WebSocketMessage<?> message) throws Exception {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                System.out.println(message);
                session.sendMessage(message);
            }
        }
    }
    
    // 是否支持部分消息 作用：是否支持将消息分成多个部分进行传输
    @Override
    public boolean supportsPartialMessages() {
        return true;
    }
}
