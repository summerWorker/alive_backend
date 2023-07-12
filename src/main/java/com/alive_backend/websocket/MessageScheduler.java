//package com.alive_backend.websocket;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.logging.Logger;
//
//@Component
//@EnableScheduling
//public class TimeTask
//{
//    private static Logger logger = LoggerFactory.getLogger(TimeTask.class);
//    @Scheduled(cron = "0/1 * * * * ?")   //每分钟执行一次
//    public void test(){
//        System.err.println("*********   定时任务执行   **************");
//        CopyOnWriteArraySet<MyWebSocket> webSocketSet =
//                MyWebSocket.getWebSocketSet();
//        int i = 0 ;
//        webSocketSet.forEach(c->{
//            try {
//                c.sendMessage("  定时发送  " + new Date().toLocaleString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        System.err.println("/n 定时任务完成.......");
//    }
//
//}