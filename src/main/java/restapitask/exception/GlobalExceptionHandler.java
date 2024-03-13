package restapitask.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleClientException(HttpClientErrorException e) {
        return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleServerException(HttpServerErrorException e) {
        return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
    }
}
