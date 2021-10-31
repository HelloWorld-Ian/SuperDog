package Core.Handlers;

import Core.Exception.DuplicateWebSocketServiceExtends;
import Core.Factory.SingletonObjInitFactory;
import Core.Net.NetService;
import Core.Net.NettyGroup;
import Core.WebSocket.webSocketService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;



public class WebSocketHandler extends SimpleChannelHandler<Object>{
    private WebSocketServerHandshaker handshake;
    private static  String WEB_SOCKET_URL;

//    static 代码块：当类被第一次使用时执行
    static {
        try {
            InetAddress inet=InetAddress.getLocalHost();
            WEB_SOCKET_URL=inet.getHostAddress();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void messageReceived(ChannelHandlerContext cxt, Object o) throws Exception {
        if(o instanceof WebSocketFrame){
            if(o instanceof CloseWebSocketFrame){
                NettyGroup.group.remove(cxt.channel());
                handshake.close(cxt.channel(),((CloseWebSocketFrame) o).retain());
                return;
            }

            String msg = ((TextWebSocketFrame) o).text();

            List<Object>webSocketServiceSubClasses=SingletonObjInitFactory.
                    getInstance().getClassesSubs(webSocketService.class.getTypeName());

            int size=webSocketServiceSubClasses.size();
            if(size>1){
                throw new DuplicateWebSocketServiceExtends();
            }else if(size==0){
                return;
            }

            webSocketService w=(webSocketService)webSocketServiceSubClasses.get(0);

            //webSocket业务代码
            w.service(cxt,msg);

        }else if(o instanceof FullHttpRequest){
            FullHttpRequest req=(FullHttpRequest)o;
            if(req.headers().contains("Upgrade")&&"websocket".contentEquals(req.headers().get("Upgrade"))){
                WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);
                handshake = wsFactory.newHandshaker(req);
                if(handshake !=null){
                    handshake.handshake(cxt.channel(),req);
                    NettyGroup.group.add(cxt.channel());
                    cxt.pipeline().remove(StaticHandler.class);
                }
            }
        }
    }
}
