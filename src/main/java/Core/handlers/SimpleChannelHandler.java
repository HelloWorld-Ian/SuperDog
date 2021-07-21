package Core.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@SuppressWarnings(value = "unchecked")
public abstract class SimpleChannelHandler<I> extends SimpleChannelInboundHandler<I> {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (this.acceptInboundMessage(msg)) {
            this.messageReceived(ctx, (I)msg);
            ctx.fireChannelRead(msg);
        } else {
            throw new Exception("type do not match");
        }
    }
}

