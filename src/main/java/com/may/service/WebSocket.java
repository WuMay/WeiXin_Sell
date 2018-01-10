package com.may.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {
    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        log.info("[websocket消息]新的链接为::{}",webSocketSet.size());
    }
    @OnClose
    public void onClost(){
        webSocketSet.remove(this);
        log.info("[websocket消息]断开的链接为::{}",webSocketSet.size());
    }
    @OnMessage
    public void onMessage(String session){
        log.info("[websocket消息]收到客户端的消息:{}",session);
    }
    public void sendMessage(String message){
        for (WebSocket webSocket : webSocketSet) {
            log.info("[websocket消息]广播消息,message:{}",message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
