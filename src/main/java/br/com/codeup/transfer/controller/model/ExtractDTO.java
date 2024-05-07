package br.com.codeup.transfer.controller.model;

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

    private BigDecimal transferValue;

    private BigDecimal tax;

    private LocalDateTime dataTransfer;

    private LocalDateTime schedulingDate;

    private String beneficiaryName;

    private String beneficiaryAccount;

}