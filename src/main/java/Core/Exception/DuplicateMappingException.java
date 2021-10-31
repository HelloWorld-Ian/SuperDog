package Core.Exception;

/**
 * if two requestMapping classes contradict with each other
 */
public class DuplicateMappingException extends Exception{
    public DuplicateMappingException(String message) {
        super(message);
    }

    public DuplicateMappingException() {
        super();
    }
}
