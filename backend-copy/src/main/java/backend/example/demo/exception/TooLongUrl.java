package backend.example.demo.exception;

public class TooLongUrl extends RuntimeException {
    public TooLongUrl(String message) {
        super(message);
    }
}