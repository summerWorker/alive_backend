package com.alive_backend.websocket;

import com.alive_backend.entity.goal.Goal;
import com.alive_backend.service.goal.GoalService;
import com.alive_backend.serviceimpl.TokenService;
import com.alive_backend.serviceimpl.goal.GoalServiceImpl;
import com.alive_backend.utils.constant.GoalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.*;


@ServerEndpoint(value = "/websocket/{token}") //响应路径为 /ws/{token} 的连接请求
//@ServerEndpoint(value = "/websocket/{userId}") //响应路径为 /ws/{token} 的连接请求
@Component
public class WebSocketServer {
    /* fix Component中Autowired注入为null问题 */
    @Resource
    private TokenService tokenService;
    private static GoalService goalService;
    private static WebSocketServer webSocketServer;
    @Autowired
    public void setGoalService(GoalService goalService) {
        WebSocketServer.goalService = goalService;
    }
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    private Map<Integer, ScheduledExecutorService> schedulerMap = new ConcurrentHashMap<>();

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private Integer userId;

    @PostConstruct
    public void init() {
        /* fix Component中Autowired注入为null问题 */
        webSocketServer = this;
        webSocketServer.tokenService = this.tokenService;
    }

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("token") String token) {
        int uid = webSocketServer.tokenService.getUserIdFromToken(token);
        this.session = session;
        this.userId = uid;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());

        Goal weight_goal = goalService.getGoalByGoalName(uid, GoalConstant.WEIGHT_GOAL);
//            System.out.println(weight_goal);
        if(weight_goal != null) {
            Date weight_goal_date = weight_goal.getGoalDdl();
            long weight_goal_time = weight_goal_date.getTime();
            long now_time = System.currentTimeMillis();
            long time = weight_goal_time - now_time;
            if(time > 0) {

                try {
                    int left_days = (int) (time / (1000 * 60 * 60 * 24));
                    sendMessage("设置了体重目标:"+ weight_goal.getGoalKey1() +" kg，还有"+left_days+"天截止");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        // 创建一个定时任务调度器
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        schedulerMap.put(uid, scheduler);

        scheduler.scheduleAtFixedRate(() -> {
            Goal bedtime_goal = goalService.getGoalByGoalName(uid, GoalConstant.BEDTIME_GOAL);
            if(bedtime_goal != null) {
                String bedtime_goal_date = bedtime_goal.getGoalKey2();
                String[] time = bedtime_goal_date.split(":");
                int hour = Integer.parseInt(time[0]);
                int minute = Integer.parseInt(time[1]);

                ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
                int now_hour = now.getHour();
                int now_minute = now.getMinute();

                int time_left = hour * 60 + minute - now_hour * 60 - now_minute;
                System.out.println(time_left);
                if(time_left == 30 || time_left ==10 || time_left == 1) {
                    System.out.println("发送消息");
                    try {
                        sendMessage("设置了睡觉目标"+ bedtime_goal.getGoalKey2() +"，还有"+time_left+"分钟");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }, 0, 1, TimeUnit.MINUTES);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());

        // 关闭并移除定时任务调度器
        ScheduledExecutorService scheduler = schedulerMap.remove(this.userId);
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 发生错误时调用
     * */
     @OnError
     public void onError(Session session, Throwable error) {
     System.out.println("发生错误");
     error.printStackTrace();
     }
     public void sendMessage(String message) throws IOException {
     this.session.getBasicRemote().sendText(message);
     //this.session.getAsyncRemote().sendText(message);
     }
     /**
      * 群发自定义消息
      * */
    public static void sendInfo(String message) throws IOException {
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
