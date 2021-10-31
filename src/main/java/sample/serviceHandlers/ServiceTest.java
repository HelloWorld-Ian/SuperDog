package sample.serviceHandlers;


import Core.Annotation.ServiceHandlerMapping;
import Core.Handlers.HttpResponseHandler;
import Core.Handlers.ServiceHandler;
import Core.Net.NetService;
import Core.Static.staticUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@ServiceHandlerMapping("/")
public class ServiceTest extends ServiceHandler {

    @ServiceHandlerMapping("/getURL")
    public void getPort(HttpResponseHandler res){
        try {
            res.write(InetAddress.getLocalHost().getHostAddress()+":"+NetService.port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    @ServiceHandlerMapping("/testService")
    public void ServiceDemo(HttpResponseHandler res){
        res.write("successful service !!");
    }

    @ServiceHandlerMapping("/testStatic")
    public void StaticDemo(HttpResponseHandler res){
        try {
            staticUtils.viewMapping(res, "static/page/transit.html");
        } catch (IOException e) {
            res.write("resource not found");
            e.printStackTrace();
        }
    }
}
