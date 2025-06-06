package backend.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({NotFoundUrl.class})
    public ResponseEntity<Map<String, String>> handleNotFoundUrl(NotFoundUrl exception) {
        Map<String, String> map = new HashMap<>();
        map.put("message", exception.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({UrlExist.class})
    public ResponseEntity<Map<String, String>> handleShortUrl(UrlExist exception) {
        Map<String, String> map = new HashMap<>();
        map.put("message", exception.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ShortUrlNotFound.class})
    public ResponseEntity<Map<String, String>> handleNotFoundShortUrl(ShortUrlNotFound exception) {
        Map<String, String> map = new HashMap<>();
        map.put("message", exception.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({TooManyAttempts.class})
    public ResponseEntity<Map<String, String>> handleTooManyAttempts(TooManyAttempts exception) {
        Map<String, String> map = new HashMap<>();
        map.put("message", exception.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({TooLongUrl.class})
    public ResponseEntity<Map<String, String>> handleTooLongUrl(TooLongUrl exception) {
        Map<String, String> map = new HashMap<>();
        map.put("message", exception.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
