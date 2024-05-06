package br.com.codeup.transfer.controller.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO extends AccountSaveDTO {

    private Long id;

    private Long userId;

}