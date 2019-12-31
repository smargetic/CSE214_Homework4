public class FullQueueException extends Exception {
    public FullQueueException(){
        super("You are at capacity, it is not possible to add anyone else.");
    }
    public FullQueueException(String message){
        super(message);
    }
}
