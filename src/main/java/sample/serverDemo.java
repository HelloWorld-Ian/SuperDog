package sample;


import Core.Annotation.ServiceHandlerPackage;
import Core.Annotation.SingletonObjHandlerPackage;
import Core.Annotation.StaticPath;
import Core.Annotation.WelcomePage;
import Core.Net.NetService;

@SingletonObjHandlerPackage("sample.singletonHandlers")
@ServiceHandlerPackage("sample.serviceHandlers")
@StaticPath("/static")
@WelcomePage("/static/page/index.html")
public class serverDemo {
    public static void main(String[] args) {
        new NetService(serverDemo.class).startServer(80);
    }
}

