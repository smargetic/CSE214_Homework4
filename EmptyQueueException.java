public class EmptyQueueException extends Exception {
    public EmptyQueueException(){
        super("There is no one in the queue.");
    }
}
