package Core.template;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * implement this interface to be the ServiceHandler (Controller)
 */
public interface ServiceHandler {
    public void handler(ChannelHandlerContext ctx, FullHttpRequest req);
}
