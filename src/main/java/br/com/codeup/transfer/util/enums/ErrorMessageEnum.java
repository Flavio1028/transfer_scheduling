package br.com.codeup.transfer.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessageEnum {

    INTERNAL_ERROR("Erro interno."),
    ERROR_VALIDATING_DATA("Erro ao validar os dados."),
    USER_NOT_FOUND("Usuário não encontrado."),
    DOCUMENT_ALREADY_REGISTERED("Documento já cadastrado");

    private final String message;

}