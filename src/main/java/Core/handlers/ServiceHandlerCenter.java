package Core.handlers;

import Core.template.ServiceHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import Core.factory.ServiceHandlerFactory;

public class ServiceHandlerCenter extends SimpleChannelHandler<FullHttpRequest> {
    @Override
    protected void messageReceived(ChannelHandlerContext cxt, FullHttpRequest req) throws Exception {
        String name= req.uri();
        ServiceHandler s= ServiceHandlerFactory.getInstance().getServiceHandlerInstance(name);
        if(s==null){
            handleResourcesNotFound(cxt);
            return;
        }
        s.handler(cxt, req);
    }
    public void handleResourcesNotFound(ChannelHandlerContext ctx){
        HttpResponseHandler.write("Resources not found",ctx);
    }
}
