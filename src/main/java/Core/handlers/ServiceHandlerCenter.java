package Core.handlers;

import Core.template.ServiceHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import Core.factory.ServiceHandlerFactory;

public class ServiceHandlerCenter extends SimpleChannelHandler<FullHttpRequest> {
    @Override
    protected void messageReceived(ChannelHandlerContext cxt, FullHttpRequest req) throws Exception {
        String name= req.uri();
        String[] parts=name.split("/");
        StringBuilder combine= new StringBuilder();
        ServiceHandler s=null;
        for (String part : parts) {
            combine.append(part);
            s = ServiceHandlerFactory.getInstance().getServiceHandlerInstance(combine.toString());
            if (s != null) {
                break;
            }
            combine.append("/");
        }

        if(s==null){
            handleResourcesNotFound(cxt);
            return;
        }
        name=name.replaceFirst(combine.toString(),"");
        s.prepareHandler(cxt,req,name);
    }
    public void handleResourcesNotFound(ChannelHandlerContext ctx){
        HttpResponseHandler.write("Resources not found",ctx);
    }
}
