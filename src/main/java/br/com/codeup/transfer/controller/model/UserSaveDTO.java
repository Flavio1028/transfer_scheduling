package br.com.codeup.transfer.controller.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveDTO {

    @NotBlank(message = "deve ser informado.")
    private String name;

    @NotBlank(message = "deve ser informado.")
    @Length(max = 11, message = "tem tamanho máximo de 11.")
    private String documentNumber;

    @NotEmpty(message = "deve ter pelo menos uma conta")
    private Set<AccountSaveDTO> accounts;

}