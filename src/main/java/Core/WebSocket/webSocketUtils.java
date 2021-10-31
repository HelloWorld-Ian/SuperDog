package Core.WebSocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class webSocketUtils {
    public static void respond(ChannelHandlerContext c,String msg){
        TextWebSocketFrame tws=new TextWebSocketFrame(msg);

        //ChannelHandlerContext.write 与 channel.write 的区别：https://www.cnblogs.com/hetutu-5238/p/12206675.html
        c.channel().writeAndFlush(tws);
    }
}
