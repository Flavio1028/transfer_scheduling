package br.com.codeup.transfer.controller.model;

import br.com.codeup.transfer.util.format.BigDecimalSerializer;
import br.com.codeup.transfer.util.format.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtractDTO {

    private String customerName;

    private String originAccount;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal transferValue;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tax;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dataTransfer;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime schedulingDate;

    private String beneficiaryName;

    private String beneficiaryAccount;

}