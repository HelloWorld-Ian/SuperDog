package Core.WebSocket;

import io.netty.channel.ChannelHandlerContext;

public abstract class webSocketService {
    public abstract void service(ChannelHandlerContext c, String msg);

    public static void sendMsg(ChannelHandlerContext c, String msg){
        webSocketUtils.respond(c,msg);
    }
}
