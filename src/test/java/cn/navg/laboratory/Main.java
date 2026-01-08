package cn.navg.laboratory;

import cn.navg.laboratory.controller.common.EmailWebSocketHandler;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        //获取当前时间,并将时间转换为字符串格式yyyy-MM-dd HH:mm:ss
        LocalDateTime now = LocalDateTime.now();
        System.out.println();
    }
}
