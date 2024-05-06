package br.com.codeup.transfer.util.exception;

import br.com.codeup.transfer.util.enums.ErrorMessageEnum;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ValidationErrorException extends RuntimeException implements Serializable {

    public ValidationErrorException(ErrorMessageEnum errorMessage) {
        super(errorMessage.getMessage());
    }

}