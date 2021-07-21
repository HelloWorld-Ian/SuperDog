package sample.service;


import Core.annotation.ServiceHandlerMapping;
import Core.handlers.HttpResponseHandler;
import Core.template.ServiceHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

@ServiceHandlerMapping("/serviceTest")
public class ServiceTest implements ServiceHandler {
    @Override
    public void handler(ChannelHandlerContext ctx, FullHttpRequest req) {
        HttpResponseHandler.write("testService",ctx);
    }
}
