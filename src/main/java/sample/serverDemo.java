package sample;


import Core.annotation.ServiceHandlerPackage;
import Core.annotation.StaticPath;
import Core.net.NetService;

@ServiceHandlerPackage("sample.service")
@StaticPath("/static")
public class serverDemo {
    public static void main(String[] args) {
        new NetService(serverDemo.class).startServer(80);
    }
}

