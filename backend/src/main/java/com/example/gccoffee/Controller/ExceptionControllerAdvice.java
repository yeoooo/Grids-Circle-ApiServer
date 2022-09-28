package com.example.gccoffee.Controller;

import com.example.gccoffee.Exception.ErrorResult;
import com.example.gccoffee.Exception.NoSuchProductException;
import com.example.gccoffee.Exception.NotEnoughStockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NotEnoughStockException.class, NoSuchProductException.class})
    public ErrorResult productExHandler(NotEnoughStockException e) {
        log.error("[ProductExceptionHandle] Not Enough Stock Exception Occurred", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    public ErrorResult productExHandler(NoSuchProductException e) {
        log.error("[ProductExceptionHandle] No Such Product Exception Occurred", e);
        return new ErrorResult("BAD", e.getMessage());
    }
}
