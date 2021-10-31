package Core.Handlers;

import Core.Net.NetService;
import Core.Static.staticUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class StaticHandler extends SimpleChannelHandler<FullHttpRequest> {
    private final String resourcesPath;
    private static final Logger log= LoggerFactory.getLogger(StaticHandler.class);


    public StaticHandler(String resourcesPath) {
        this.resourcesPath = resourcesPath;
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest req) {
        String path = req.uri();
        handleResource(ctx,req,path);
    }

    private void handleResource(ChannelHandlerContext ctx, FullHttpRequest req, String path){
        InputStream in;

        if((path.equals("/")||path.equals(""))&&NetService.welcome!=null){
            in=this.getClass().getResourceAsStream(NetService.welcome);
            if(in==null){
                return;
            }
            handleFile(ctx, req, NetService.welcome.substring(NetService.welcome.indexOf(".")),in);
        }else{
            if(!path.startsWith(resourcesPath)){
                return;
            }
            in=this.getClass().getResourceAsStream(path);
            if(in==null){
                return;
            }
            handleFile(ctx, req, path.substring(path.indexOf(".")),in);
        }

    }

    /**
     * find the static file
     */
    public void handleFile(ChannelHandlerContext ctx, FullHttpRequest req, String suffix,InputStream in){
        RandomAccessFile f;
        try {

            HttpHeaders headers=handleHeader(suffix);
            byte[]bts=in.readAllBytes();

            //netty 以 byteBuf 的形式返回响应体
            FullHttpResponse response = new DefaultFullHttpResponse
                    (HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(bts));
            response.headers().set(headers);

            ctx.writeAndFlush(response);

            //ChanelFuture: 用于保存I/O异步操作的结果
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            future.addListener(ChannelFutureListener.CLOSE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleFileNotFound(ChannelHandlerContext ctx){
        HttpResponseHandler.write("Resources not found",ctx);
    }

    /**
     * <p>
     *     find the type of the file and define the response header
     *     that matches the type of the file
     * </p>
     *
     * @return the header that matches the type of the file
     */
    public static HttpHeaders handleHeader(String suffix){
        HttpHeaders header=new DefaultHttpHeaders();
        header.set(HttpHeaderNames.CONTENT_TYPE, (String)staticUtils.typeToHeaders.get(suffix));
        return header;
    }
}
