package andrehsvictor.api.dailydo.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import andrehsvictor.api.dailydo.exception.DefaultExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllExceptions(Exception exception, HttpServletRequest request) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(
                DefaultExceptionResponse.builder().message(exception.getMessage()).timeStamp(new Date())
                        .path(request.getServletPath())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
