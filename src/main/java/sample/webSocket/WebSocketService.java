package sample.webSocket;

import Core.WebSocket.webSocketService;
import Core.WebSocket.webSocketUtils;
import io.netty.channel.ChannelHandlerContext;

public class WebSocketService extends webSocketService {
    @Override
    public void service(ChannelHandlerContext c, String msg) {
        String response="服务端成功接收到消息："+msg;
        webSocketUtils.respond(c,response);
    }
}
