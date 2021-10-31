package sample.singletonHandlers;

import Core.Annotation.Singleton;
import Core.Annotation.SingletonObjHandler;
import sample.webSocket.WebSocketService;

@SingletonObjHandler
public class singletonHandler {

    @Singleton
    public WebSocketService getWebSocketService(){
        return new WebSocketService();
    }
}
