package Core.Exception;

/**
 * if the abstract class webSocketService has more than one extend
 */
public class DuplicateWebSocketServiceExtends extends Exception{
    public DuplicateWebSocketServiceExtends(String message) {
        super(message);
    }

    public DuplicateWebSocketServiceExtends() {
        super();
    }
}
