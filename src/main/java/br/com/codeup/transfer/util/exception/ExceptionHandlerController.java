package br.com.codeup.transfer.util.exception;

import br.com.codeup.transfer.controller.model.ErrorDTO;
import br.com.codeup.transfer.util.enums.ErrorMessageEnum;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public ErrorDTO exception() {
        return ErrorDTO.builder()
                .message(ErrorMessageEnum.INTERNAL_ERROR.getMessage())
                .data(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ErrorDTO integrityViolationException(DataIntegrityViolationException exception) {
        return ErrorDTO.builder()
                .message(exception.getCause().getLocalizedMessage())
                .data(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ErrorDTO constraintViolationException(ConstraintViolationException exception) {
        return ErrorDTO.builder()
                .message(ErrorMessageEnum.ERROR_VALIDATING_DATA.getMessage())
                .data(LocalDateTime.now())
                .details(exception.getConstraintViolations().stream()
                        .map(violation -> String.format("O campo %s %s", StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                                        .reduce((first, second) -> second).orElse(null),
                                violation.getMessage())).collect(Collectors.toList()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ValidationErrorException.class})
    public ErrorDTO validationErrorException(ValidationErrorException exception) {
        return ErrorDTO.builder()
                .message(ErrorMessageEnum.ERROR_VALIDATING_DATA.getMessage())
                .data(LocalDateTime.now())
                .details(List.of(exception.getMessage()))
                .build();
    }

}