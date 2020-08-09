package exception.handler;

import dto.error.NullErrorDto;
import exception.ApiException;
import exception.exceptions.nullExceptions.ApiNullException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import util.Converter;

@RestControllerAdvice
public class ErrorsHandlers{

    @ExceptionHandler(value = {ApiNullException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NullErrorDto ResourceNotFoundExceptionHandler(ApiException apiEx){
        return Converter.ExceptionToDto((ApiNullException) apiEx);
    }

}
