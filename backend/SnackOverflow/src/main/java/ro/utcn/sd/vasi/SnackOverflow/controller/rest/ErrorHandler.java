package ro.utcn.sd.vasi.SnackOverflow.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.utcn.sd.vasi.SnackOverflow.dto.ErrorDTO;

@Component
@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    //ce se intampla daca ex nu e de tipu clasei din ExceptionHandler?
    public ErrorDTO handlerError(Exception ex) {
        return new ErrorDTO(ex.getClass().getSimpleName());
    }

}
