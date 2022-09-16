package pl.shonsu.GithubReader.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.handler.WebFluxResponseStatusExceptionHandler;

@RestControllerAdvice
public class GithubRestResponseExceptionHandler extends WebFluxResponseStatusExceptionHandler {

    @ExceptionHandler(GHNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleGHUserNotFoundExcetion(GHNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage()));
    }
}
