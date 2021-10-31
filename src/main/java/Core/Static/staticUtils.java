package Core.Static;

import Core.Handlers.HttpResponseHandler;
import Core.Handlers.StaticHandler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.IOException;
import java.io.InputStream;

public class staticUtils {
    /**
     * the map that mapping the file type and the header
     */
    public static  JSONObject typeToHeaders;

    static{

        /*
        * classloader.getResourceAsStream:默认从classpath路径获取资源
        * class.getResourceAsStream:如果路径前有“/”，则从classpath路径获取资源，否则以类所在包路径下获取资源
        */

//        InputStream in=staticUtils.class.getClassLoader().getResourceAsStream("fileType.json");
        InputStream in=staticUtils.class.getResourceAsStream("/fileType.json");
        try {
            assert in != null;
            typeToHeaders =JSON.parseObject(new String(in.readAllBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void viewMapping(HttpResponseHandler res,String staticPath) throws IOException {
        ChannelHandlerContext c=res.ctx;
        if(staticPath.startsWith("/")){
            staticPath=staticPath.substring(1);
        }
        InputStream in=staticUtils.class.getClassLoader().getResourceAsStream(staticPath);

        if(in==null){
            FullHttpResponse response=new DefaultFullHttpResponse
                    (HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
            c.writeAndFlush(response);
        }else{
            byte[]bytes=in.readAllBytes();
            HttpHeaders h=StaticHandler.handleHeader(staticPath.substring(staticPath.indexOf(".")));
            FullHttpResponse response=new DefaultFullHttpResponse
                    (HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(bytes));
            response.headers().set(h);
            c.writeAndFlush(response);
        }

        ChannelFuture future = c.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        future.addListener(ChannelFutureListener.CLOSE);
    }
}
