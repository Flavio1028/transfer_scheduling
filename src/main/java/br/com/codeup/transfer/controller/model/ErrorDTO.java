package br.com.codeup.transfer.controller.model;

import br.com.codeup.transfer.util.format.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO {

    private String message;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime data;

    private List<String> details;

}