package br.com.codeup.transfer.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessageEnum {

    INTERNAL_ERROR("Erro interno."),
    ERROR_VALIDATING_DATA("Erro ao validar os dados."),
    USER_NOT_FOUND("Usuário não encontrado."),
    DOCUMENT_ALREADY_REGISTERED("Documento já cadastrado"),
    SOURCE_ACCOUNT_NOT_FOUND("Conta de origem não localizada."),
    DESTINATION_COUNT_NOT_FOUND("Conta de destino não localizada."),
    iNVALID_DATA_TRANSFER("A data de trasferência não pode ser anterior ao dia atual."),
    INVALID_RATE("Taxa não aplicavel para este tipo de agendamento.");

    private final String message;

}