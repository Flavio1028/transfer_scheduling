package br.com.codeup.transfer.controller.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountSaveDTO {

    private String accountNumber;

    private BigDecimal balance;

}