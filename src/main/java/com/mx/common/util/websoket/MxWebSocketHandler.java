package com.mx.common.util.websoket;

import com.mx.common.constant.CommonConstant;
import com.mx.common.pojo.PushMessageBean;
import com.mx.common.util.ArrayUtil;
import com.mx.common.util.StringUtil;
import com.mx.generator.pojo.SysUser;
import org.springframework.web.socket.*;

import java.util.ArrayList;


public class MxWebSocketHandler implements WebSocketHandler {

    // 保存所有的用户session
    private static final ArrayList<WebSocketSession> users = new ArrayList<>();

    // 连接 就绪时
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("connect websocket success.......");
        users.add(session);
    }


    // 处理信息
    @Override
    public void handleMessage(WebSocketSession session,
                              WebSocketMessage<?> message) throws Exception {
    }

    // 处理传输时异常
    @Override
    public void handleTransportError(WebSocketSession session,
                                     Throwable exception) throws Exception {
        // TODO Auto-generated method stub
    }

    // 关闭 连接时
    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus closeStatus) throws Exception {

        System.out.println("connect websocket closed.......");

        users.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        // TODO Auto-generated method stub
        return false;
    }

    // 推送信息给对象的用户
    public static void pushMessage(String userIds, WebSocketMessage<?> webSocketMessage) throws Exception {
        // id为空不推送
        if (StringUtil.isEmpty(userIds)) {
            return;
        }

        for (WebSocketSession user : users) {
            SysUser sessionUser = (SysUser) user.getAttributes().get(CommonConstant.SESSION_USER);
            if (StringUtil.isNotEmpty(userIds)) {
                // 判断推送id是否包含session中用户
                if (ArrayUtil.contains(userIds.split(","), sessionUser.getId().toString())) {
                    // 推送消息
                    user.sendMessage(webSocketMessage);
                }
            }
        }
    }

}