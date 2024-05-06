package br.com.codeup.transfer.controller.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends UserSaveDTO {

    private Long id;

}